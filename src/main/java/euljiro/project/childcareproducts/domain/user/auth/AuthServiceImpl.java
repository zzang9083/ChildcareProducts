package euljiro.project.childcareproducts.domain.user.auth;

import euljiro.project.childcareproducts.common.config.jwt.JwtTokenProvider;
import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.common.exception.JwtExcepion;
import euljiro.project.childcareproducts.common.response.ErrorCode;
import euljiro.project.childcareproducts.domain.user.*;
import euljiro.project.childcareproducts.domain.user.kakao.KaKaoUserInfo;
import euljiro.project.childcareproducts.domain.user.kakao.KakaoApicaller;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final KakaoApicaller kakaoApicaller;
    private final UserReader userReader;
    private final UserStore userstore;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenUtil tokenUtil;

    @Override
    public UserInfo.LoginResponse isSignUp(String accessToken) {

        log.info("***** AuthServiceImpl.isSignUp input *****");
        log.info("accessToken:"+ accessToken);
        log.info("***********************************************");


        // api를 통해 고객정보 가져오기
        KaKaoUserInfo kaKaoUserInfo = kakaoApicaller.getUserInfo(accessToken);
        log.info("kaKaoUserInfo.getId() : "+ kaKaoUserInfo.getId().toString());

        // 고객 조회
        User user = getUser(kaKaoUserInfo);

        // 토큰발급
        String jwtToken = jwtTokenProvider.createToken(user.getUserKey());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserKey());

        // 리프레시토큰 저장
        tokenUtil.saveRefreshToken(user.getUserKey(), refreshToken);

        return new UserInfo.LoginResponse(user, jwtToken, refreshToken);
    }

    private User getUser(KaKaoUserInfo kaKaoUserInfo) {
        User user;
        try {
            user = userReader.getUserByUserkey(kaKaoUserInfo.getId().toString());
        } catch(EntityNotFoundException e) {
            log.info("userstore.store");
            user = kaKaoUserInfo.toEntity();
            userstore.store(user);
        }

        log.info("***** User Entity Data *****");
        log.info("user.getId() : "+ user.getId());
        log.info("user.getUserKey() : "+ user.getUserKey());
        log.info("***********************************************");

        return user;
    }


    @Override
    public UserInfo.ReissueResponse reissueToken(UserCommand.ReissueRequest request) {

        String inputRefreshToken = request.getRefreshToken();

        log.info("inputRefreshToken : " + inputRefreshToken);

        // Refresh Token 검증
        jwtTokenProvider.validateToken(inputRefreshToken);

        // Access Token 에서 userKey을 가져옴
        Authentication authentication = jwtTokenProvider.getAuthentication(inputRefreshToken);

        log.info("authentication.getName() : " + authentication.getName());

        // Redis에서 저장된 Refresh Token 값을 가져옴
        String redisRefreshToken = tokenUtil.getValues(authentication.getName());
        if(!redisRefreshToken.equals(inputRefreshToken)) {
            throw new JwtExcepion(ErrorCode.EXPIRED_TOKEN, ErrorCode.EXPIRED_TOKEN.getErrorMsg());
        }

        // 토큰발급
        String jwtToken = jwtTokenProvider.createToken(authentication.getName());
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication.getName());

        return new UserInfo.ReissueResponse(jwtToken, refreshToken);

    }
}

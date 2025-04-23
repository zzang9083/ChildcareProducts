package euljiro.project.childcareproducts.application.complex;

import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.common.config.jwt.JwtTokenProvider;
import euljiro.project.childcareproducts.common.exception.JwtExcepion;
import euljiro.project.childcareproducts.common.response.ErrorCode;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistory;
import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistoryService;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import euljiro.project.childcareproducts.domain.user.login.KaKaoUserInfo;
import euljiro.project.childcareproducts.domain.user.login.KakaoApicaller;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final KakaoApicaller kakaoApicaller;

    private final UserService userService;

    private final GroupService groupService;

    private final ChildService childService;

    private final ProductInquiryHistoryService inquiryHistoryService;


    private final JwtTokenProvider jwtTokenProvider;

    private final TokenUtil tokenUtil;

    @Transactional
    public LoginInfo.LoginResponse login(String accessToken) {
        log.debug("LoginService.login start");

        // api를 통해 고객정보 가져오기
        KaKaoUserInfo kaKaoUserInfo = kakaoApicaller.getUserInfo(accessToken);
        String userKey = kaKaoUserInfo.getId().toString();
        log.debug("Apicaller.getUserInfo :: userKey : {}", userKey);

        // 고객 조회 or 생성
        User user = userService.getUserOrRegister(userKey);
        log.debug("userService.getUserOrRegister :: userId : {}", user.getId());

        // 토큰 생성
        String jwtToken = jwtTokenProvider.createToken(user.getUserKey());
        // 리프레시토큰 생성/저장
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserKey());
        tokenUtil.saveRefreshToken(user.getUserKey(), refreshToken);
        log.debug("tokenUtil save token/RefreshToken :: jwtToken : {}, refreshToken : {}", jwtToken, refreshToken);

        log.debug("********** LoginService.login end");
        return new LoginInfo.LoginResponse(user, jwtToken, refreshToken);

    }

    @Transactional
    public LoginInfo.ReissueResponse reissueToken(String inputRefreshToken) {
        log.debug("LoginService.reissueToken start");

        // accessToken의 userKey 가지고오기
        Authentication authentication = jwtTokenProvider.getAuthentication(inputRefreshToken);
        String userKey = authentication.getName();
        log.info("userkey :  {} /   refreshToken : {}" + userKey, inputRefreshToken);

        // RefreshToken 검증
        validateRefreshToken(userKey, inputRefreshToken);

        // 고객 조회
        User user = userService.getUserAndGroup(userKey);

        // 토큰발급
        String jwtToken = jwtTokenProvider.createToken(authentication.getName());
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication.getName());

        log.debug("***** LoginService.reissueToken end *****");
        log.info("userKey:"+ userKey);
        log.info("jwtToken:"+ jwtToken);
        log.info("refreshToken:"+ refreshToken);
        log.info("***********************************************");

        return new LoginInfo.ReissueResponse(user, jwtToken, refreshToken);

    }

    @Transactional
    public LoginInfo.DashBoardResponse getDashBoardInfo(String groupToken) {
        log.debug("LoginService.getDashBoardInfo start");

        //Group
        Group group = groupService.getGroupBy(groupToken);
        log.info("groupService.getGroupByToken:: groupId : {}"+ group.getId());


        //child
        log.info("childService.getChildBy:: childId : {}"+ group.getSelectedChildId());
        Child child = childService.getChildBy(group.getSelectedChildId());

        //inquiry History
        List<ProductInquiryHistory> histories = inquiryHistoryService.getTop5hiStoriesByGroupId(group.getId());
        log.info("inquiryHistoryService.getTop5hiStoriesByGroupId:: histories : {}"+ histories);

        return new LoginInfo.DashBoardResponse(child, histories);
    }

    private void validateRefreshToken(String userKey, String inputRefreshToken) {
        log.debug("LoginService.validateRefreshToken start");
        String redisRefreshToken = tokenUtil.getValues(userKey);
        if (!redisRefreshToken.equals(inputRefreshToken)) {
            log.error("*** validateRefreshToken not matched " +
                            "  :: userKey : {} // inputRefreshToken : {}",userKey, inputRefreshToken);
            throw new JwtExcepion(ErrorCode.EXPIRED_TOKEN, ErrorCode.EXPIRED_TOKEN.getErrorMsg());
        }
        log.debug("LoginService.validateRefreshToken end");
    }
}

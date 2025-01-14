package euljiro.project.childcareproducts.infrastructure.user.sharecode;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.common.response.ErrorCode;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCode;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ShareCodeUtil implements ShareCodeService {

    private final ShareCodeRepository shareCodeRepository;

    @Override
    public ShareCode generateShareCode(String userKey) {

        // 기존재시, 기존코드 리턴
        Optional<ShareCode> issuedCode = getIssuedCodeByUserKey(userKey);
        if(issuedCode.isPresent()) {
            return issuedCode.get();
        }

        // 존재하지않으면, 생성 후 리턴
        var initShareCode = makeShareCode();
        ShareCode shareCode = shareCodeRepository.save(new ShareCode(userKey, initShareCode));

        return shareCode;
    }

    @Override
    public String getUserKeyByShareCode(String inputShareCode) {

        ShareCode shareCode = shareCodeRepository.findByShareCode(inputShareCode)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않는 공유코드입니다. 공유코드를 확인해주세요."));

        return shareCode.getUserKey();
    }

    private String makeShareCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999)+ 111111);
    }

    public Optional<ShareCode> getIssuedCodeByUserKey(String userKey) {
        return shareCodeRepository.findByUserKey(userKey);

    }


//    public boolean isValid(String userToken) {
//        return stringRedisTemplate.hasKey(userToken);
//    }
//
//    public String getCode(String userToken) {
//        Optional<ShareCode> shareCode = shareCodeRepository.findByUserToken(userToken);
//        return shareCode.isPresent();
//    }
//
//    public String isExistCode(String shareCode) {
//        stringRedisTemplate.opsForValue().
//    }



}

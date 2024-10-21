package euljiro.project.childcareproducts.domain.user.login;

import euljiro.project.childcareproducts.domain.user.login.KaKaoUserInfo;

public interface KakaoApicaller {

    KaKaoUserInfo getUserInfo(String token);
}

package euljiro.project.childcareproducts.domain.user.auth;

import euljiro.project.childcareproducts.domain.user.UserCommand;
import euljiro.project.childcareproducts.domain.user.UserInfo;

public interface AuthService {

    public UserInfo.LoginResponse isSignUp(String accessToken);

    public UserInfo.ReissueResponse reissueToken(UserCommand.ReissueRequest request);
}

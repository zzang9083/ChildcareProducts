package euljiro.project.childcareproducts.domain.user;

import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;

public interface UserService {



    LoginInfo registerUser(UserCommand command);

    User getUser(String userKey);

    User getUserAndGroup(String userKey);

    User getUserOrRegister(String userKey);


    User registerUserInfo(User user, UserCommand.RegisterUserInfoRequest userRequest);

    //List<UserInfo> getUserInfoList(List<String> userToken);
}


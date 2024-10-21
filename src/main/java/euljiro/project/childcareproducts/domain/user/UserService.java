package euljiro.project.childcareproducts.domain.user;

import euljiro.project.childcareproducts.application.user.dto.LoginInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;

public interface UserService {



    LoginInfo registerUser(UserCommand command);

    User getUser(String userKey);

    User getUserOrRegister(String userKey);


    void checkValidUser(String userKey);

    void registerUserInfo(User user, String nickName,String relationship);

    //List<UserInfo> getUserInfoList(List<String> userToken);
}


package euljiro.project.childcareproducts.domain.user;

import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;

public interface UserService {



    LoginInfo registerUser(UserCommand command);

    User getUser(String userKey);

    User getUserBy(long userId);

    User getUserAndGroup(String userKey);

    User getUserOrRegister(String userKey, String pushToken);

    User registerUserInfo(User user, UserCommand.RegisterUserInfoRequest userRequest);


    void updateStatus(User user, User.Status status);

    boolean isGroupEmpty(Long groupId);

    //List<UserInfo> getUserInfoList(List<String> userToken);
}


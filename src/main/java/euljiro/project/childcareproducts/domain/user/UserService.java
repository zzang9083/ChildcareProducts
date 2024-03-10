package euljiro.project.childcareproducts.domain.user;

import java.util.*;

public interface UserService {
    UserInfo registerUser(UserCommand command);

    UserInfo getUserInfo(String userKey);

    void checkValidUser(String userKey);

    //List<UserInfo> getUserInfoList(List<String> userToken);
}


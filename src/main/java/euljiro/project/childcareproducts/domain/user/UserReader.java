package euljiro.project.childcareproducts.domain.user;

import java.util.List;

public interface UserReader {

    //User getUserById(String userId);

    User getUserByUserkey(String userkey);

    User getUserAndGroupByUserkey(String userkey);

    //List<User> getUserList(List<String> userTokenList);
}

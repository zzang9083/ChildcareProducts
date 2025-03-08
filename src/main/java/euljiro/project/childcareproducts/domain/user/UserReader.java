package euljiro.project.childcareproducts.domain.user;

public interface UserReader {

    //User getUserById(String userId);

    User getUserByUserkey(String userkey);

    User getUserAndGroupByUserkey(String userkey);

    int countByGroupId(Long groupId);

    //List<User> getUserList(List<String> userTokenList);
}

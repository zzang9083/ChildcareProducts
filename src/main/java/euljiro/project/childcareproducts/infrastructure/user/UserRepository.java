package euljiro.project.childcareproducts.infrastructure.user;

import euljiro.project.childcareproducts.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);


    Optional<User> findByUserKey(String userkey);

    int countByGroupId(Long groupId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.group WHERE u.userKey = :userkey")
    Optional<User> getUserAndGroupByUserkey(String userkey);


    //List<User> findByUserTokenIn(List<String> userTokenList);
}

package euljiro.project.childcareproducts.infrastructure.user;

import euljiro.project.childcareproducts.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);


    Optional<User> findByUserKey(String userkey);

    //List<User> findByUserTokenIn(List<String> userTokenList);
}

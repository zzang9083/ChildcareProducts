package euljiro.project.childcareproducts.infrastructure.user;

import euljiro.project.childcareproducts.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.*;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);


    Optional<User> findByUserKey(String userkey);

    //List<User> findByUserTokenIn(List<String> userTokenList);
}

package euljiro.project.childcareproducts.infrastructure.child;

import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findAllByRegisteredUserKey(String userkey);
}

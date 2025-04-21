package euljiro.project.childcareproducts.infrastructure.child;

import euljiro.project.childcareproducts.domain.child.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findAllByRegisteredUserKeyAndStatus(String userkey, Child.Status status);

    Optional<Child> findByChildToken(String childToken);
}

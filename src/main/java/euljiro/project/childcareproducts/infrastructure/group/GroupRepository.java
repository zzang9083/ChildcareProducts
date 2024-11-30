package euljiro.project.childcareproducts.infrastructure.group;

import euljiro.project.childcareproducts.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupToken(String groupToken);
}

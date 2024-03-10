package euljiro.project.childcareproducts.infrastructure.user.group;

import euljiro.project.childcareproducts.domain.user.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}

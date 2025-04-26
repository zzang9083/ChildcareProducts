package euljiro.project.childcareproducts.infrastructure.child;

import euljiro.project.childcareproducts.domain.child.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findAllByRegisteredUserKeyAndStatus(String userkey, Child.Status status);

    Optional<Child> findByChildToken(String childToken);

    @Query("SELECT c FROM Child c WHERE c.group.id = :groupId AND c.status = :status")
    List<Child> findByGroupIdAndStatus(@Param("groupId") Long groupId, @Param("status") Child.Status status);
}

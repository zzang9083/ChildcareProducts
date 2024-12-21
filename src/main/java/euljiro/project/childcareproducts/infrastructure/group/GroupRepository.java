package euljiro.project.childcareproducts.infrastructure.group;

import euljiro.project.childcareproducts.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupToken(String groupToken);

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.cardList WHERE g.groupToken = :groupToken")
    Optional<Group> findByCardsByGroupToken(@Param("groupToken")String groupToken);




}

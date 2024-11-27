package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuchaseHistoryRepository extends JpaRepository<PuchaseHistory, Long> {
}

package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PuchaseHistoryRepository extends JpaRepository<PuchaseHistory, Long> {

    @Query("SELECT ph FROM PuchaseHistory ph WHERE " +
            "ph.group = :group AND " +
            "(:category IS NULL OR ph.category = :category) AND " +
            "(:purchaseRoute IS NULL OR ph.purchaseRoute = :purchaseRoute) AND " +
            "(:startDate IS NULL OR ph.purchasedDateTime >= :startDate) AND " +
            "(:endDate IS NULL OR ph.purchasedDateTime <= :endDate)")
    List<PuchaseHistory> findFilteredPurchaseHistories(Group group, String category, String purchaseRoute, LocalDate startDate, LocalDate endDate);

}

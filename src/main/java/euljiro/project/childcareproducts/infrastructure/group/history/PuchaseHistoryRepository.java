package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PuchaseHistoryRepository extends JpaRepository<PuchaseHistory, Long> {

    @Query("SELECT ph FROM PuchaseHistory ph WHERE " +
            "ph.group = :group AND " +
            "(:category IS NULL OR ph.category = :category) AND " +
            "(:purchaseRoute IS NULL OR ph.purchaseRoute = :purchaseRoute) AND " +
            "(:startDateTime IS NULL OR ph.purchasedDateTime >= :startDateTime) AND " +
            "(:endDateTime IS NULL OR ph.purchasedDateTime <= :endDateTime)")
    List<PuchaseHistory> findFilteredPurchaseHistories(Group group, Item.Category category, Product.PurchaseRoute purchaseRoute, LocalDateTime startDateTime, LocalDateTime endDateTime);

}

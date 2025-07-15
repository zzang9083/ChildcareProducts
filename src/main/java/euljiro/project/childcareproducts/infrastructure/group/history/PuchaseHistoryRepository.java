package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PuchaseHistoryRepository extends JpaRepository<PuchaseHistory, Long> {


    Optional<PuchaseHistory> findByItemId(Long itemId);

    @Query("SELECT sum(ph.price) FROM PuchaseHistory ph WHERE " +
            "ph.group = :group AND " +
            "ph.status = :status AND " +
            "(:category IS NULL OR ph.category = :category) AND " +
            "(:purchaseRoute IS NULL OR ph.purchaseRoute = :purchaseRoute) AND " +
            "(:startDateTime IS NULL OR ph.purchasedDateTime >= :startDateTime) AND " +
            "(:endDateTime IS NULL OR ph.purchasedDateTime <= :endDateTime)")
    BigDecimal getTotalPrice(Group group, Item.Category category, Product.PurchaseRoute purchaseRoute, LocalDateTime startDateTime, LocalDateTime endDateTime, PuchaseHistory.Status status);


    @Query("SELECT ph FROM PuchaseHistory ph WHERE " +
            "ph.group = :group AND " +
            "ph.status =  :status AND " +
            "(:category IS NULL OR ph.category = :category) AND " +
            "(:purchaseRoute IS NULL OR ph.purchaseRoute = :purchaseRoute) AND " +
            "(:startDateTime IS NULL OR ph.purchasedDateTime >= :startDateTime) AND " +
            "(:endDateTime IS NULL OR ph.purchasedDateTime <= :endDateTime)")
    Page<PuchaseHistory> findFilteredPurchaseHistories(Group group, Item.Category category, Product.PurchaseRoute purchaseRoute
                                                        , LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable, PuchaseHistory.Status status);

}

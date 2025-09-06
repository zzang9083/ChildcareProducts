package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
            "ph.purchasedDateTime >= :startDateTime AND " +
            "ph.purchasedDateTime < :endDateTime")
    Page<PuchaseHistory> getPurchaseHistories(Group group, LocalDateTime startDateTime, LocalDateTime endDateTime, PuchaseHistory.Status status, Pageable pageable);


    @Query("""
        SELECT new euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto(
            :selectedMonth,
            COUNT(p),
            SUM(CASE WHEN p.payment = 'CARD' OR p.payment = 'CASH' THEN 1 ELSE 0 END),
            SUM(CASE WHEN p.payment = 'SHARING' THEN 1 ELSE 0 END),
            SUM(p.price)
        )
        FROM PuchaseHistory p
        WHERE p.group.id = :groupId
          AND p.purchasedDateTime >= :start
          AND p.purchasedDateTime < :end
          AND p.status = 'PURCHASED'
    """)
    SelectedMonthStatsDto getSelectedMonthStats(@Param("groupId") Long groupId,
                                                @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("selectedMonth") String selectedMonth);


    // 과거 5개월치 월별 총 금액
    @Query(value = """
    SELECT DATE_FORMAT(p.purchased_date_time, '%Y-%m') AS ym,
           SUM(p.price) AS total
    FROM purchase_history p
    WHERE p.group_id = :groupId
      AND p.purchased_date_time >= :start
      AND p.purchased_date_time < :end
      AND p.status = 'PURCHASED'
    GROUP BY ym
    ORDER BY ym DESC
    """, nativeQuery = true)
    List<Object[]> getPastFiveMonthsAmounts(@Param("groupId") Long groupId,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    @Query("""
    SELECT p
    FROM PuchaseHistory p
    WHERE p.group.id = :groupId
      AND p.purchasedDateTime >= :start
      AND p.purchasedDateTime < :end    
      AND p.status = 'PURCHASED'
    ORDER BY p.purchasedDateTime DESC
    """)
    List<PuchaseHistory> findTop5RecentPurchasedByGroupId(
            @Param("groupId") Long groupId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );
}

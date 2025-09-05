package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
                                                @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("selectedMonth") YearMonth selectedMonth);

    // 과거 5개월치 월별 총 금액
    @Query("""
        SELECT new euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto(
            FUNCTION('DATE', FUNCTION('DATE_TRUNC', 'month', p.purchasedDateTime)), 
            SUM(p.price)
        )
        FROM PuchaseHistory p
        WHERE p.group.id = :groupId
          AND p.purchasedDateTime < :endDate
          AND p.purchasedDateTime >= :startDate
          AND p.status = 'PURCHASED'
        GROUP BY FUNCTION('DATE_TRUNC', 'month', p.purchasedDateTime)
        ORDER BY FUNCTION('DATE_TRUNC', 'month', p.purchasedDateTime) DESC
    """)
    List<MonthlyAmountDto> getPastFiveMonthsAmounts(@Param("groupId") Long groupId,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

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

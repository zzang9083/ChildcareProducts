package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ProductInquiryHistoryRepository extends JpaRepository<ProductInquiryHistory, Long> {


    @Query("""
    SELECT h FROM ProductInquiryHistory h
    WHERE h.groupId = :groupId
      AND h.childId = :selectedChildId
    AND h.creationTime = (
        SELECT MAX(h2.creationTime)
        FROM ProductInquiryHistory h2
        WHERE h2.groupId = h.groupId
        AND h2.productToken = h.productToken
    )
    ORDER BY h.creationTime DESC
""")
    List<ProductInquiryHistory> findRecentDistinctByGroupIdAndChildId(@Param("groupId") Long groupId,@Param("selectedChildId") long selectedChildId);

}

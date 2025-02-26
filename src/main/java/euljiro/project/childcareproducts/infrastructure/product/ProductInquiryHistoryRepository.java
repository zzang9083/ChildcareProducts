package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductInquiryHistoryRepository extends JpaRepository<ProductInquiryHistory, Long> {


    List<ProductInquiryHistory> findTop5ByGroupIdOrderByCreationTimeDesc(Long groupId);

}

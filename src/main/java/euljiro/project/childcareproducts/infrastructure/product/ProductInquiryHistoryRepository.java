package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.product.ProductInquiryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInquiryHistoryRepository extends JpaRepository<ProductInquiryHistory, Long> {


}

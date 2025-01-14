package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.ProductInquiryHistory;
import euljiro.project.childcareproducts.domain.product.ProductInquiryHistoryStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductInquiryHistoryStoreImpl implements ProductInquiryHistoryStore {

    private final ProductInquiryHistoryRepository productInquiryHistoryRepository;

    @Override
    public ProductInquiryHistory store(ProductInquiryHistory initProductInquiryHistory) {
        return productInquiryHistoryRepository.save(initProductInquiryHistory);
    }
}

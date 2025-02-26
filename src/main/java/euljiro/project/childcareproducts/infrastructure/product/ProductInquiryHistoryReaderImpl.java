package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistory;
import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductInquiryHistoryReaderImpl implements ProductInquiryHistoryReader {


    private final ProductInquiryHistoryRepository productInquiryHistoryRepository;

    @Override
    public List<ProductInquiryHistory> getTop5hiStoriesByGroupId(long groupId) {
        return productInquiryHistoryRepository.findTop5ByGroupIdOrderByCreationTimeDesc(groupId);

    }
}

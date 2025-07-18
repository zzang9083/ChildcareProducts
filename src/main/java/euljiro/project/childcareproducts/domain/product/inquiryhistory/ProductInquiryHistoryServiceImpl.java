package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInquiryHistoryServiceImpl implements ProductInquiryHistoryService{

    private final ProductReader productReader;
    private final ProductInquiryHistoryReader productInquiryHistoryReader;

    private final ProductInquiryHistoryStore productInquiryHistoryStore;

    @Override
    public void storeProductInquiryHistory(String productToken, long childId) {
        Product product = productReader.findBy(productToken);
        productInquiryHistoryStore.store(new ProductInquiryHistory(product,childId));
    }

    @Override
    public List<ProductInquiryHistory> getTop5hiStoriesByGroupIdAndSelectedChild(long groupId, long selectedChildId) {

        return productInquiryHistoryReader.getTop5hiStoriesByGroupIdAndSelectedChild(groupId, selectedChildId);
    }
}

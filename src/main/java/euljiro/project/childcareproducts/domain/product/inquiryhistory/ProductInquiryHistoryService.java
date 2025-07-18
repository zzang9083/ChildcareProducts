package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import java.util.List;

public interface ProductInquiryHistoryService {

    void storeProductInquiryHistory(String productToken, long childId);

    List<ProductInquiryHistory> getTop5hiStoriesByGroupIdAndSelectedChild(long groupId, long selectedChildId);
}

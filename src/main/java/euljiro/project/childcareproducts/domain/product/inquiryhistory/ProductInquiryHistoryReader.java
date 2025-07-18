package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import java.util.List;

public interface ProductInquiryHistoryReader {

    List<ProductInquiryHistory> getTop5hiStoriesByGroupIdAndSelectedChild(long groupId, long selectedChildId);
}

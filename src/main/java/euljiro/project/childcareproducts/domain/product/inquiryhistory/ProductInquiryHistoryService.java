package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import java.util.List;

public interface ProductInquiryHistoryService {

    List<ProductInquiryHistory> getTop5hiStoriesByGroupId(long groupId);
}

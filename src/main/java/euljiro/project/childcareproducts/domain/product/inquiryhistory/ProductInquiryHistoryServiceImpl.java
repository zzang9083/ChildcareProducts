package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInquiryHistoryServiceImpl implements ProductInquiryHistoryService{

    private final ProductInquiryHistoryReader productInquiryHistoryReader;

    @Override
    public List<ProductInquiryHistory> getTop5hiStoriesByGroupId(long groupId) {

        return productInquiryHistoryReader.getTop5hiStoriesByGroupId(groupId);
    }
}

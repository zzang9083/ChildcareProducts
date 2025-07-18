package euljiro.project.childcareproducts.application.product;

import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.product.ProductService;
import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductApplicationService {

    private final ProductService productService;

    private final ChildService childService;
    
    private final ProductInquiryHistoryService productInquiryHistoryService;


    public ProductInfo.Main getProduct(String productToken, String childToken) {
        Child child = childService.getChildBy(childToken);

        // History Save
        productInquiryHistoryService.storeProductInquiryHistory(productToken, child.getId());
        
        return productService.getProductBy(productToken);
    }


    public void updateProduct(ProductCommand.UpdateProductRequest command) {
        productService.updateProduct(command);
    }

    public void deleteProduct(String productToken) {
        productService.deleteProduct(productToken);
    }
}

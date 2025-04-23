package euljiro.project.childcareproducts.application.product;

import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.product.ProductService;
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




    public ProductInfo.Main getProduct(String productToken) {
        return productService.getProductBy(productToken);
    }


    public void updateProduct(ProductCommand.UpdateProductRequest command) {
        productService.updateProduct(command);
    }

    public void deleteProduct(String productToken) {
        productService.deleteProduct(productToken);
    }
}

package euljiro.project.childcareproducts.application.product;

import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.product.ProductService;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
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

    private final TokenUtil tokenUtil;



    public ProductInfo.Main getProduct(String productToken) {
        long productId = tokenUtil.getIdByToken(productToken);


        return productService.getProduct(productId);
    }


    public void updateProduct(ProductCommand.UpdateProductRequest command) {
        long productId = tokenUtil.getIdByToken(command.getProductToken());
        command.setProductId(productId);
        productService.updateProduct(command);
    }

    public void deleteProduct(String productToken) {
        long productId = tokenUtil.getIdByToken(productToken);
        productService.deleteProduct(productId);
    }
}

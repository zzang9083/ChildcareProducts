package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.product.ProductStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {

    private final ProductRepository productRepository;

    @Override
    public Product store(Product initProduct) {
        return productRepository.save(initProduct);
    }

    @Override
    public void deleteProductBy(String productToken) {
        productRepository.deleteProductByProductToken(productToken);
    }


}

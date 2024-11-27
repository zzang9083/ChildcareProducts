package euljiro.project.childcareproducts.domain.product;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductReader productReader;

    private final ProductStore productStore;

    @Override
    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {
        var initProduct = command.toEntity();
        Product Product = productStore.store(initProduct);
        return Product.getProductToken();
    }

    @Override
    public ProductInfo.Main getProduct(String ProductToken) {
        Product Product = productReader.findByProductToken(ProductToken);

        return new ProductInfo.Main(Product);
    }



    @Override
    public void updateProduct(ProductCommand.UpdateProductRequest command) {

        Product product = productReader.findByProductToken(command.getProductToken());

        product.updateInfo(command.getProductName(), command.getPurchaseRoute()
                , command.getUrl(),command.getProductStatus(), command.getDescription());

        productStore.store(product);

    }

    @Override
    public void deleteProduct(String ProductToken) {
        productStore.deleteProductByProductToken(ProductToken);
    }

}

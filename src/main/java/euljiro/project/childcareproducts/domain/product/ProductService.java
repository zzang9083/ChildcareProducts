package euljiro.project.childcareproducts.domain.product;


import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;

public interface ProductService {

    String registerProduct(ItemProductCommand.RegisterProductRequest command);

    void updateProduct(ProductCommand.UpdateProductRequest command);

    ProductInfo.Main getProduct(String ProductToken);

    void deleteProduct(String ProductToken);


}

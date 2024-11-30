package euljiro.project.childcareproducts.domain.product;


import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.item.Item;

public interface ProductService {

    ItemProductInfo.RegisterProductResponse registerProduct(Item item, ItemProductCommand.RegisterProductRequest command);

    void updateProduct(ProductCommand.UpdateProductRequest command);

    ProductInfo.Main getProduct(long ProductId);

    void deleteProduct(long ProductId);



}

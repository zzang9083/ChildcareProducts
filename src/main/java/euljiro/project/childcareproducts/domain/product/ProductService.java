package euljiro.project.childcareproducts.domain.product;


import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.user.User;

public interface  ProductService {

    ItemProductInfo.RegisterProductResponse registerProduct(Item item, User user, ItemProductCommand.RegisterProductRequest command);

    void updateProduct(ProductCommand.UpdateProductRequest command);

    ProductInfo.Main getProductBy(String productToken);

    void deleteProduct(String productToken);



}

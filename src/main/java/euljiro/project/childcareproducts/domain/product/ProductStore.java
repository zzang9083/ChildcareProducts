package euljiro.project.childcareproducts.domain.product;

import euljiro.project.childcareproducts.domain.item.Item;

public interface ProductStore {

    Product store(Product initProduct);

    void deleteProductByProductId(long productId);
}

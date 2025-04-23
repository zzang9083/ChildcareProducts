package euljiro.project.childcareproducts.domain.product;

public interface ProductStore {

    Product store(Product initProduct);

    void deleteProductBy(String productToken);
}

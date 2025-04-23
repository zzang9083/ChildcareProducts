package euljiro.project.childcareproducts.domain.product;


import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductReader {

    Product findBy(long productId);

    Product findBy(String productToken);

    int getCountBy(Item item);

    Page<Product> findByItemId(long itemId, Pageable pageable);


    Product findByProductIdWithItem(long ProductId);

}

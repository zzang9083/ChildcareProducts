package euljiro.project.childcareproducts.domain.product;


import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductReader {

    Product findByProductId(long ProductId);

    int getCountBy(Item item);

    Page<Product> findByItemId(long itemId, Pageable pageable);


    Product findByProductIdWithItem(long ProductId);

}

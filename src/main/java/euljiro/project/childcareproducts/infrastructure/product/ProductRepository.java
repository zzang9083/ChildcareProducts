package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findById(long productId);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.item WHERE p.id = :productId")
    Optional<Product> findByIdWithItem(long productId);

    void deleteProductById(long productId);
}

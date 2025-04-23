package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findById(long productId);

    Optional<Product> findByProductToken(String productToken);

    void deleteProductByProductToken(String productToken);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.item WHERE p.id = :productId")
    Optional<Product> findByIdWithItem(long productId);

    int countProductsByItem(Item item);

    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.item WHERE p.item.id = :itemId")
    Page<Product> findWithItemByItemId(@Param("itemId") long itemId, Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.item.id = :itemId")
     Page<Product> findByItemId(@Param("itemId") long itemId, Pageable pageable);
}

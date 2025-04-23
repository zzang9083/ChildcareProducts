package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(long itemId);

    Optional<Item> findByItemToken(String itemToken);


    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.productList p WHERE i.id = :itemId AND p.id = :productId")
    Optional<Item> findWithSpecificProduct(@Param("itemId") long itemId, @Param("productId") long productId);

    Page<Item> findAllByGroupIdAndChildIdAndStatus(long groupId, long childId, Item.Status status, Pageable pageable);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.productList p WHERE i.id = :itemId ORDER BY p.creationTime DESC")
    Optional<Item> findWithProductsBy(@Param("itemId") long itemId);


    void deleteItemByItemToken(String itemToken);
}

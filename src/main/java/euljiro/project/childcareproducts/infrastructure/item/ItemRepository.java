package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(long itemId);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.productList WHERE i.id = :itemId")
    Optional<Item> findWithProductsByItemId(@Param("itemId") long itemId);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.productList p WHERE i.id = :itemId AND p.id = :productId")
    Optional<Item> findWithSpecificProduct(@Param("itemId") long itemId, @Param("productId") long productId);


    List<Item> findAllByGroupId(long groupId);

    void deleteItemById(long itemId);
}

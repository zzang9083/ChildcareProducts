package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemToken(String itemToken);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.productList WHERE i.itemToken = :itemToken")
    Optional<Item> findWithProductsByItemToken(@Param("itemToken") String itemToken);

    List<Item> findAllByGroupToken(String groupToken);

    void deleteItemByItemToken(String itemToken);
}

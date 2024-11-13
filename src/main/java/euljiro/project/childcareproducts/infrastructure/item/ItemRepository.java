package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemToken(String itemToken);

    void deleteItemByItemToken(String itemToken);
}

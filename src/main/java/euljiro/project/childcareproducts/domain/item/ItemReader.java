package euljiro.project.childcareproducts.domain.item;

import java.util.*;
import euljiro.project.childcareproducts.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemReader {

    Item findByItemId(long itemId);

    Item findWithSpecificProduct(long itemId, long productId);

    Page<Item> findItemsBy(long groupId, long childId, Item.Status status, Pageable pageable);

    Item findItemAndProductsBy(long itemId);
}

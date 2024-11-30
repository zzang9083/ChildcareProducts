package euljiro.project.childcareproducts.domain.item;

import java.util.*;
import euljiro.project.childcareproducts.domain.user.User;

public interface ItemReader {

    Item findByItemId(long itemId);

    Item findWithProductsByItemId(long itemId);

    List<Item> findByItemList(long groupId);
}

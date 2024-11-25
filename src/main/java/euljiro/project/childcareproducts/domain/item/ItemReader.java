package euljiro.project.childcareproducts.domain.item;

import java.util.*;
import euljiro.project.childcareproducts.domain.user.User;

public interface ItemReader {

    Item findByItemToken(String itemToken);

    Item findWithProductsByItemToken(String itemToken);

    List<Item> findByItemList(String groupToken);
}

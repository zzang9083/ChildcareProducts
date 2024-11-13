package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.domain.user.User;

public interface ItemStore {

    Item store(Item initItem);

    void deleteItemByItemToken(String itemToken);
}

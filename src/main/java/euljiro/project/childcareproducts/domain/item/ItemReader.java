package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.domain.user.User;

public interface ItemReader {

    Item findByItemToken(String itemToken);
}

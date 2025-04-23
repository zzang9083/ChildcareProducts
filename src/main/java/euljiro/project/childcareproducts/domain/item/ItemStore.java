package euljiro.project.childcareproducts.domain.item;

public interface ItemStore {

    Item store(Item initItem);

    void deleteItemBy(String itemToken);
}

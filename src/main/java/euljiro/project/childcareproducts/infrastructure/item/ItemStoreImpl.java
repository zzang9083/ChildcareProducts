package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {

    private final ItemRepository itemRepository;

    @Override
    public Item store(Item initItem) {
        return itemRepository.save(initItem);
    }

    @Override
    public void deleteItemByItemId(long itemId) {
        itemRepository.deleteItemById(itemId);
    }
}

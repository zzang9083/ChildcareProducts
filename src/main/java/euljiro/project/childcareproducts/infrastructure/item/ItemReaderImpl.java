package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item findByItemId(long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 품목정보입니다."));
    }

    @Override
    public Item findWithSpecificProduct(long itemId, long productId) {
        return itemRepository.findWithSpecificProduct(itemId, productId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 물품정보입니다."));
    }

    @Override
    public Page<Item> findItemsBy(long groupId, long childId, Item.Status status, Pageable pageable) {

        return itemRepository.findAllByGroupIdAndChildIdAndStatus(groupId, childId, status , pageable);
    }

    @Override
    public Item findItemAndProductsBy(long itemId) {
        return itemRepository.findWithProductsBy(itemId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 품목정보입니다."));
    }
}

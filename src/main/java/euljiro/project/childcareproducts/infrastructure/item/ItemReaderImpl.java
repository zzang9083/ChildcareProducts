package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 물품정보입니다."));
    }


    @Override
    public Item findWithProductsByItemId(long itemId) {
        return itemRepository.findWithProductsByItemId(itemId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 물품정보입니다."));
    }

    @Override
    public List<Item> findByItemList(long groupId) {
        var items = itemRepository.findAllByGroupId(groupId);

        if(items == null || items.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 품목 정보입니다.");
        }

        return items;
    }
}

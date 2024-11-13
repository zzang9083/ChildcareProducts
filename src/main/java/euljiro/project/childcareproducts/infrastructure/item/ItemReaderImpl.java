package euljiro.project.childcareproducts.infrastructure.item;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item findByItemToken(String itemToken) {
        return itemRepository.findByItemToken(itemToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 물품정보입니다."));
    }
}

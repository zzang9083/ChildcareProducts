package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemReader itemReader;

    private final ItemStore itemStore;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command) {
        var initItem = command.toEntity();
        Item item = itemStore.store(initItem);
        return item.getItemToken();
    }

    @Override
    public void updateItem(ItemCommand.UpdateItemRequest command) {

        Item item = itemReader.findByItemToken(command.getItemToken());

        item.checkValidStatus();

        item.updateInfo(command.getItemName(), command.getCategory()
                , command.getMinPrice(),command.getMaxPrice(), command.getDescription());

        itemStore.store(item);

    }

    @Override
    public void changeStatus(ItemCommand.ChangeStatusRequest command) {
        Item item = itemReader.findByItemToken(command.getItemToken());

        item.checkValidStatus();

        item.changeStatus(command.getStatus());

        itemStore.store(item);
    }

    @Override
    public ItemInfo.Main getItem(String itemToken) {
        Item item = itemReader.findByItemToken(itemToken);

        return new ItemInfo.Main(item);
    }

    @Override
    public void deleteItem(String itemToken) {
        itemStore.deleteItemByItemToken(itemToken);
    }
}

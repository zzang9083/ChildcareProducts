package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemReader itemReader;

    private final ItemStore itemStore;

    @Override
    public GroupItemInfo.RegisterItemResponse registerItem(GroupItemCommand.RegisterItemRequest command) {
        var initItem = command.toEntity();
        Item item = itemStore.store(initItem);

        return new GroupItemInfo.RegisterItemResponse(item);
    }

    @Override
    public GroupItemInfo.MainList getItems(long groupId) {
        List<Item> items = itemReader.findByItemList(groupId);

        return new GroupItemInfo.MainList(items);
    }

    @Override
    public void updateItem(ItemCommand.UpdateItemRequest command) {

        Item item = itemReader.findByItemId(command.getItemId());

        item.checkValidStatus();

        item.updateInfo(command.getItemName(), command.getCategory()
                , command.getMinPrice(),command.getMaxPrice(), command.getDescription());

        itemStore.store(item);

    }

    @Override
    public void changeStatus(ItemCommand.ChangeStatusRequest command) {
        Item item = itemReader.findByItemId(command.getItemId());

        item.changeStatus(command.getStatus());

        itemStore.store(item);
    }

    @Override
    public Item getItem(long itemId) {
        return itemReader.findByItemId(itemId);
    }

    @Override
    public ItemInfo.MainDetail getItemAndProduct(long itemId) {
        Item item = itemReader.findWithProductsByItemId(itemId);

        return new ItemInfo.MainDetail(item);
    }

    @Override
    public GroupItemInfo.SpecificItemAndProductResponse findWithSpecificProduct(long itemId, long productId) {
        Item findItem = itemReader.findWithSpecificProduct(itemId, productId);

        return new GroupItemInfo.SpecificItemAndProductResponse(findItem);
    }

    public void confirmPurchase(long itemId, long selectedProductId) {

        // 구매완료처리
        Item item = itemReader.findByItemId(itemId);
        item.confirmPurchase(selectedProductId);

        itemStore.store(item);

    }

    @Override
    public void deleteItem(long itemId) {
        itemStore.deleteItemByItemId(itemId);
    }
}

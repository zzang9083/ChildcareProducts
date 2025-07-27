package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.domain.common.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemReader itemReader;

    private final ItemStore itemStore;

    @Override
    public GroupItemInfo.RegisterItemResponse registerItem(long groupId, long childId, long userId, Gender userGender
            , GroupItemCommand.RegisterItemRequest command) {
        var initItem = command.toEntity(groupId, childId, userId, userGender);
        Item item = itemStore.store(initItem);

        return new GroupItemInfo.RegisterItemResponse(item);
    }



    @Override
    public void updateItem(ItemCommand.UpdateItemRequest command) {

        Item item = itemReader.findByItemToken(command.getItemToken());

        item.checkValidStatus();

        item.updateInfo(command.getItemName(), command.getCategory(), command.getItemStatus()
                , command.getMinPrice(),command.getMaxPrice(), command.getDescription());

        itemStore.store(item);

    }

    @Override
    public Item changeStatus(ItemCommand.ChangeStatusRequest command) {
        Item item = itemReader.findByItemToken(command.getItemToken());

        item.changeStatus(command.getStatus());

        itemStore.store(item);

        return item;
    }

    @Override
    public Item getItemBy(long itemId) {
        return itemReader.findByItemId(itemId);
    }

    @Override
    public Item getItemBy(String itemToken) {
        return itemReader.findByItemToken(itemToken);
    }



    @Override
    public GroupItemInfo.SpecificItemAndProductResponse findWithSpecificProduct(long itemId, long productId) {
        Item findItem = itemReader.findWithSpecificProduct(itemId, productId);

        return new GroupItemInfo.SpecificItemAndProductResponse(findItem);
    }

    @Override
    public GroupItemInfo.MainList getItems(long groupId,long childId, Item.Status status, Pageable pageable) {
        Page<Item> items = null;

        if(status != null) {
            items = itemReader.findItemsBy(groupId, childId, status, pageable);

        }
        else {
            List<Item> mergedList = new ArrayList<>();

            Page<Item> onPurchaseItems = itemReader.findItemsBy(groupId, childId, Item.Status.ON_PURCHASE, pageable);
            Page<Item> completePurchaseItems = itemReader.findItemsBy(groupId, childId, Item.Status.COMPLETE_PURCHASE, pageable);
            Page<Item> holdItems = itemReader.findItemsBy(groupId, childId, Item.Status.HOLD, pageable);

            mergedList.addAll(onPurchaseItems.getContent());
            mergedList.addAll(completePurchaseItems.getContent());
            mergedList.addAll(holdItems.getContent());

            // 전체 개수를 계산
            long totalSize = onPurchaseItems.getTotalElements() + completePurchaseItems.getTotalElements() + holdItems.getTotalElements();

            // 새로운 Page<Item> 생성
            items = new PageImpl<>(mergedList, pageable, totalSize);
        }

        return new GroupItemInfo.MainList(items);
    }

    @Override
    public ItemProductInfo.Main getItemAndProducts(long itemId){
        Item item = itemReader.findItemAndProductsBy(itemId);

        return new ItemProductInfo.Main(item);
    }


    public void confirmPurchase(long itemId, long selectedProductId) {

        // 구매완료처리
        Item item = itemReader.findByItemId(itemId);
        item.confirmPurchase(selectedProductId);

        itemStore.store(item);

    }

    @Override
    public void deleteItem(String itemToken) {

        itemStore.deleteItemBy(itemToken);
    }
}

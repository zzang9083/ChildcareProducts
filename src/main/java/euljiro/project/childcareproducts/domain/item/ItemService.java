package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    GroupItemInfo.RegisterItemResponse registerItem(long groupId, long childId, GroupItemCommand.RegisterItemRequest command);

    void updateItem(ItemCommand.UpdateItemRequest command);

    Item changeStatus(ItemCommand.ChangeStatusRequest command);

    Item getItemBy(long itemId);

    Item getItemBy(String itemToken);

    GroupItemInfo.SpecificItemAndProductResponse  findWithSpecificProduct(long itemId, long productId);

    GroupItemInfo.MainList getItems(long groupId, long childId, Item.Status status, Pageable pageable);

    ItemProductInfo.Main getItemAndProducts(long itemId);

    void confirmPurchase(long itemId, long selectedProductId);


    void deleteItem(String itemToken);


}

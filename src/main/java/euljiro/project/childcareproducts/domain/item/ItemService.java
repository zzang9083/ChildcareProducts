package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;

public interface ItemService {

    GroupItemInfo.RegisterItemResponse registerItem(GroupItemCommand.RegisterItemRequest command);

    void updateItem(ItemCommand.UpdateItemRequest command);

    void changeStatus(ItemCommand.ChangeStatusRequest command);

    Item getItem(long itemId);

    ItemInfo.MainDetail getItemAndProduct(long itemId);

    GroupItemInfo.SpecificItemAndProductResponse  findWithSpecificProduct(long itemId, long productId);

    GroupItemInfo.MainList getItems(long groupId);

    void confirmPurchase(long itemId, long selectedProductId);


    void deleteItem(long itemId);


}

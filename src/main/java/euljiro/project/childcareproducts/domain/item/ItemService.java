package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    GroupItemInfo.RegisterItemResponse registerItem(GroupItemCommand.RegisterItemRequest command);

    void updateItem(ItemCommand.UpdateItemRequest command);

    void changeStatus(ItemCommand.ChangeStatusRequest command);

    Item getItem(long itemId);

    GroupItemInfo.SpecificItemAndProductResponse  findWithSpecificProduct(long itemId, long productId);

    GroupItemInfo.MainList getItems(long groupId, long childId, Pageable pageable);

    ItemProductInfo.Main getItemAndProducts(long itemId);

    void confirmPurchase(long itemId, long selectedProductId);


    void deleteItem(long itemId);


}

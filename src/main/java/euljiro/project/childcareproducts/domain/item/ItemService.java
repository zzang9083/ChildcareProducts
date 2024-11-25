package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;

public interface ItemService {

    String registerItem(GroupItemCommand.RegisterItemRequest command);

    void updateItem(ItemCommand.UpdateItemRequest command);

    void changeStatus(ItemCommand.ChangeStatusRequest command);

    ItemInfo.Main getItem(String itemToken);

    ItemInfo.MainDetail getItemAndProduct(String itemToken);

    GroupItemInfo.MainList getItems(String groupToken);


    void deleteItem(String itemToken);


}

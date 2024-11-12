package euljiro.project.childcareproducts.domain.item;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;

public interface ItemService {

    String registerItem(ItemCommand.RegisterItemRequest command);

    void updateItem(ItemCommand.UpdateItemRequest command);

    void changeStatus(ItemCommand.ChangeStatusRequest command);

    ItemInfo.Main getItem(String itemToken);

    void deleteItem(String itemToken);


}

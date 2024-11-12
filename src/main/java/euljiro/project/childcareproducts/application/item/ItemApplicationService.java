package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.domain.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemApplicationService {

    private final ItemService itemService;

    public String registerItem(ItemCommand.RegisterItemRequest command) {
        return itemService.registerItem(command);
    }

    public ItemInfo.Main getItem(String itemToken) {
        return itemService.getItem(itemToken);
    }

    public void updateItem(ItemCommand.UpdateItemRequest command) {
        itemService.updateItem(command);
    }

    public void changeStatus(ItemCommand.ChangeStatusRequest command) { itemService.changeStatus(command); }


    public void deleteItem(String itemToken) {
        itemService.deleteItem(itemToken);
    }
}

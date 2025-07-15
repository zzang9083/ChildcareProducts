package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import euljiro.project.childcareproducts.domain.item.Item;
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

    private final PuchaseHistoryService puchaseHistoryService;



    public ItemInfo.Main getItem(String itemToken) {
        return new ItemInfo.Main(itemService.getItemBy(itemToken));
    }

    public void updateItem(ItemCommand.UpdateItemRequest command) {
       itemService.updateItem(command);
    }

    public void changeStatus(ItemCommand.ChangeStatusRequest command) {
        Item item = itemService.getItemBy(command.getItemToken());

        itemService.changeStatus(command);

        // 구매완료 -> 다른상태 : 구매이력 삭제
        if(item.getStatus()    == Item.Status.COMPLETE_PURCHASE) {
            puchaseHistoryService.deletePurchaseHistory(item.getId());
        }
    }

    public void deleteItem(String itemToken) {
        itemService.deleteItem(itemToken);
    }


}

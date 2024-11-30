package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.domain.item.ItemService;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
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

    private final TokenUtil tokenUtil;


    public ItemInfo.Main getItem(String itemToken) {
        long itemId = tokenUtil.getIdByToken(itemToken);
        return new ItemInfo.Main(itemService.getItem(itemId));
    }

    public ItemInfo.MainDetail getItemAndProduct(String itemToken) {
        long itemId = tokenUtil.getIdByToken(itemToken);
        return itemService.getItemAndProduct(itemId);
    }

    public void updateItem(ItemCommand.UpdateItemRequest command) {
        log.info("***** ItemApplicationService.updateItem start *****");
        long itemId = tokenUtil.getIdByToken(command.getItemToken());
        log.info("***** itemId : "+ itemId);
        command.setItemId(itemId);

        itemService.updateItem(command);
    }

    public void changeStatus(ItemCommand.ChangeStatusRequest command) {
        long itemId = tokenUtil.getIdByToken(command.getItemToken());
        command.setItemId(itemId);
        itemService.changeStatus(command);
    }

    public void deleteItem(String itemToken) {
        long itemId = tokenUtil.getIdByToken(itemToken);

        itemService.deleteItem(itemId);
    }


}

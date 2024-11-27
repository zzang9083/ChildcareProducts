package euljiro.project.childcareproducts.application.group;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
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
public class GroupItemService {

    private final ItemService itemService;


    public String registerItem(GroupItemCommand.RegisterItemRequest command) {
        return itemService.registerItem(command);
    }

    public GroupItemInfo.MainList getItems(String groupToken) {
        return itemService.getItems(groupToken);
    }

    public void addPurchaseHistory() {

    }
}

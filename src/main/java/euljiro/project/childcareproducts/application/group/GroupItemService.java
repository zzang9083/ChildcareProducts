package euljiro.project.childcareproducts.application.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
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
public class GroupItemService {

    private final ItemService itemService;

    private final TokenUtil tokenUtil;


    public String registerItem(GroupItemCommand.RegisterItemRequest command) {

        long groupId = tokenUtil.getIdByToken(command.getGroupToken());
        command.setGroupId(groupId);

        GroupItemInfo.RegisterItemResponse response
                                = itemService.registerItem(command);

        tokenUtil.storeIdByToken(response.getItemToken(), response.getItemId());

        return response.getItemToken();
    }

    public GroupItemInfo.MainList getItems(String groupToken) {
        long groupId = tokenUtil.getIdByToken(groupToken);
        return itemService.getItems(groupId);
    }

    public void addPurchaseHistory() {

    }
}

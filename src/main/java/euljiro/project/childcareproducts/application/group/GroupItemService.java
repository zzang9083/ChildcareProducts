package euljiro.project.childcareproducts.application.group;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.domain.item.ItemService;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        long childId = tokenUtil.getIdByToken(command.getChildToken());
        command.setChildId(childId);


        GroupItemInfo.RegisterItemResponse response
                                = itemService.registerItem(command);

        tokenUtil.storeIdByToken(response.getItemToken(), response.getItemId());

        return response.getItemToken();
    }

    public GroupItemInfo.MainList getItems(GroupItemCommand.GetItemsRequest req, int page, int size) {

        long groupId = tokenUtil.getIdByToken(req.getGroupToken());
        long childId = tokenUtil.getIdByToken(req.getChildToken());

        Pageable pageable = PageRequest.of(page, size);

        return itemService.getItems(groupId, childId, req.getStatus(), pageable);
    }

    public void addPurchaseHistory() {

    }
}

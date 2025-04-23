package euljiro.project.childcareproducts.application.group;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.item.ItemService;
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

    private final GroupService groupService;

    private final ItemService itemService;

    private final ChildService childService;



    public String registerItem(GroupItemCommand.RegisterItemRequest command) {

        Group group = groupService.getGroupBy(command.getGroupToken());

        Child child = childService.getChildBy(command.getChildToken());


        GroupItemInfo.RegisterItemResponse response
                                = itemService.registerItem(group.getId(), child.getId(), command);

        return response.getItemToken();
    }

    public GroupItemInfo.MainList getItems(GroupItemCommand.GetItemsRequest command, int page, int size) {

        Group group = groupService.getGroupBy(command.getGroupToken());

        Child child = childService.getChildBy(command.getChildToken());

        Pageable pageable = PageRequest.of(page, size);

        return itemService.getItems(group.getId(), child.getId(), command.getStatus(), pageable);
    }

    public void addPurchaseHistory() {

    }
}

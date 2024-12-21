package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PuchaseHistoryServiceImpl implements PuchaseHistoryService{

    private final PuchaseHistoryStore puchaseHistoryStore;

    private final GroupReader groupReader;

    @Override
    public void addPurchaseHistory(ItemCommand.ConfirmPurchaseRequest command) {
        Group group = groupReader.findByGroupId(command.getGroupId());
        PuchaseHistory initHistory = command.toHistoryEntity(group);
        puchaseHistoryStore.store(initHistory);
    }
}

package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PuchaseHistoryServiceImpl implements PuchaseHistoryService{

    private final PuchaseHistoryStore puchaseHistoryStore;

    private final PuchaseHistoryReader puchaseHistoryReader;

    private final GroupReader groupReader;

    @Override
    public void addPurchaseHistory(ItemCommand.ConfirmPurchaseRequest command) {
        Group group = groupReader.findByGroupId(command.getGroupId());
        PuchaseHistory initHistory = command.toHistoryEntity(group);
        puchaseHistoryStore.store(initHistory);
    }

    @Override
    public PuchaseHistoryInfo.GetPuchasesResponse getPurchases(PuchaseHistoryCommand.GetPuchasesRequest command) {

        Group group = groupReader.findByGroupToken(command.getGroupToken());
        List<PuchaseHistory> filteredPurchaseHistories = puchaseHistoryReader.findFilteredPurchaseHistories(group, command);

        return new PuchaseHistoryInfo.GetPuchasesResponse(filteredPurchaseHistories);

    }
}

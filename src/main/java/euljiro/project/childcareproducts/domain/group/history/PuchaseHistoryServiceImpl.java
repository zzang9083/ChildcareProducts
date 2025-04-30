package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PuchaseHistoryServiceImpl implements PuchaseHistoryService{

    private final PuchaseHistoryStore puchaseHistoryStore;

    private final PuchaseHistoryReader puchaseHistoryReader;

    private final GroupReader groupReader;

    @Override
    public void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command) {
        PuchaseHistory initHistory = command.toHistoryEntity();
        puchaseHistoryStore.store(initHistory);
    }

    @Override
    public PuchaseHistoryInfo.GetPuchasesResponse getPurchases(PuchaseHistoryCommand.GetPuchasesRequest command, int page, int size) {

        Group group = groupReader.findByGroupToken(command.getGroupToken());

        BigDecimal totalPrice = puchaseHistoryReader.getTotalPrice(group, command);

        Pageable pageable = PageRequest.of(page, size);
        Page<PuchaseHistory> filteredPurchaseHistories = puchaseHistoryReader.findFilteredPurchaseHistories(group, command, pageable);

        return new PuchaseHistoryInfo.GetPuchasesResponse(totalPrice, filteredPurchaseHistories);

    }
}

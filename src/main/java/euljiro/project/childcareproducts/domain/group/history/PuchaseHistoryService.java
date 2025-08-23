package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.Group;

import java.time.YearMonth;

public interface PuchaseHistoryService {

    void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command);

    void deletePurchaseHistory(long itemId);

    PuchaseHistoryInfo.GetPurchaseHistoriesResponse getPurchaseHistories(Group group, YearMonth selectedMonth, int page, int size);

    PuchaseHistoryInfo.GetMainResponse getMainInfo(long groupId, YearMonth selectedMonth);

}

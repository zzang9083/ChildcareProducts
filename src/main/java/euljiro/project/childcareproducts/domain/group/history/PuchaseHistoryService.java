package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;

import java.time.LocalDate;

public interface PuchaseHistoryService {

    void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command);

    void deletePurchaseHistory(long itemId);

    PuchaseHistoryInfo.GetPuchasesResponse getPurchases(PuchaseHistoryCommand.GetPuchasesRequest command, int page, int size);

    PuchaseHistoryInfo.GetMainResponse getMainInfo(long groupId, LocalDate selectedDate);

}

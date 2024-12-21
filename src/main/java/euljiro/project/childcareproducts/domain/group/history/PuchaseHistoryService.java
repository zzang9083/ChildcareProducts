package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;

public interface PuchaseHistoryService {

    void addPurchaseHistory(ItemCommand.ConfirmPurchaseRequest command);

}

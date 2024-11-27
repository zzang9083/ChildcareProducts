package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;

public interface PuchaseHistoryService {

    void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command);

}

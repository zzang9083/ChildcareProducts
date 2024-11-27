package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;

public class PuchaseHistoryServiceImpl implements PuchaseHistoryService{


    @Override
    public void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command) {
        PuchaseHistory initHistory = command.toHistoryEntity();
    }
}

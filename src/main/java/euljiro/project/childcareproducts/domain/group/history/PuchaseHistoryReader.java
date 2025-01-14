package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;

import java.util.List;

public interface PuchaseHistoryReader {
    List<PuchaseHistory> findFilteredPurchaseHistories(Group group, PuchaseHistoryCommand.GetPuchasesRequest command);
}

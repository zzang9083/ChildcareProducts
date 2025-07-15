package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface PuchaseHistoryReader {

    PuchaseHistory getPuchaseHistoryBy(long itemId);

    BigDecimal getTotalPrice(Group group, PuchaseHistoryCommand.GetPuchasesRequest command);
    Page<PuchaseHistory> findFilteredPurchaseHistories(Group group, PuchaseHistoryCommand.GetPuchasesRequest command, Pageable pageable);


}

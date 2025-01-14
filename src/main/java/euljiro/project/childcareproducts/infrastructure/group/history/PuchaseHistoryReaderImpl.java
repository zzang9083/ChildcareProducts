package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PuchaseHistoryReaderImpl implements PuchaseHistoryReader {

    private final PuchaseHistoryRepository puchaseHistoryRepository;

    @Override
    public List<PuchaseHistory> findFilteredPurchaseHistories(Group group, PuchaseHistoryCommand.GetPuchasesRequest command) {

        return puchaseHistoryRepository
                .findFilteredPurchaseHistories(group, command.getCategory().toString(), command.getPurchaseRoute().toString()
                                                                , command.getStartDate(), command.getEndDate());
    }
}

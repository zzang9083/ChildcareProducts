package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PuchaseHistoryReaderImpl implements PuchaseHistoryReader {

    private final PuchaseHistoryRepository puchaseHistoryRepository;

    @Override
    public List<PuchaseHistory> findFilteredPurchaseHistories(Group group, PuchaseHistoryCommand.GetPuchasesRequest command) {

//        String category = Optional.ofNullable(command.getCategory())
//                .map(Enum::toString)
//                .orElse(null);
//
//        String purchaseRoute = Optional.ofNullable(command.getPurchaseRoute())
//                .map(Enum::toString)
//                .orElse(null);

        LocalDateTime startDateTime = command.getStartDate() != null
                ? command.getStartDate().atStartOfDay()
                : null;

        LocalDateTime endDateTime = command.getEndDate() != null
                ? command.getEndDate().atTime(LocalTime.MAX)
                : null;


        return puchaseHistoryRepository
                .findFilteredPurchaseHistories(group, command.getCategory(), command.getPurchaseRoute()
                                                                , startDateTime, endDateTime);
    }
}

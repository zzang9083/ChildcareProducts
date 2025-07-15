package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class PuchaseHistoryReaderImpl implements PuchaseHistoryReader {

    private final PuchaseHistoryRepository puchaseHistoryRepository;

    public PuchaseHistory getPuchaseHistoryBy(long itemId) {
        return puchaseHistoryRepository.findByItemId(itemId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 구매정보입니다."));
    }

    @Override
    public BigDecimal getTotalPrice(Group group, PuchaseHistoryCommand.GetPuchasesRequest command) {

        LocalDateTime startDateTime = command.getStartDate() != null
                ? command.getStartDate().atStartOfDay()
                : null;

        LocalDateTime endDateTime = command.getEndDate() != null
                ? command.getEndDate().atTime(LocalTime.MAX)
                : null;


        return puchaseHistoryRepository
                .getTotalPrice(group, command.getCategory(), command.getPurchaseRoute()
                        , startDateTime, endDateTime);
    }

    @Override
    public Page<PuchaseHistory> findFilteredPurchaseHistories(Group group, PuchaseHistoryCommand.GetPuchasesRequest command, Pageable pageable) {

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
                                                                , startDateTime, endDateTime, pageable);
    }
}

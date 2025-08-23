package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryReader;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

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
                        , startDateTime, endDateTime, PuchaseHistory.Status.PURCHASED);
    }

    @Override
    public Page<PuchaseHistory> getPurchaseHistories(Group group, YearMonth selectedMonth, int page, int size) {

        LocalDateTime startDateTime = selectedMonth.atDay(1).atStartOfDay();

        LocalDateTime endDateTime = selectedMonth.plusMonths(1).atDay(1).atStartOfDay();

        Pageable pageable = PageRequest.of(page, size, Sort.by("purchasedDateTime").descending());

        return puchaseHistoryRepository.getPurchaseHistories(group, startDateTime , endDateTime, PuchaseHistory.Status.PURCHASED, pageable);

    }

    @Override
    public SelectedMonthStatsDto getMonthlyPurchaseStats(long groupId, YearMonth selectedMonth) {
        return puchaseHistoryRepository.getSelectedMonthStats(groupId, selectedMonth);

    }

    @Override
    public List<MonthlyAmountDto> getPastFiveMonthsAmounts(long groupId, LocalDate startDate, LocalDate endDate) {
        return puchaseHistoryRepository.getPastFiveMonthsAmounts(groupId, startDate, endDate);
    }

    @Override
    public List<PuchaseHistory> getTop5RecentPurchaseHistories(long groupId,  YearMonth selectedMonth, Pageable pageable) {
        return puchaseHistoryRepository.findTop5RecentPurchasedByGroupId(groupId, selectedMonth, pageable);
    }
}

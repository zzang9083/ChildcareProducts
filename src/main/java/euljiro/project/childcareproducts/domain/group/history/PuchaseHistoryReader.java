package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PuchaseHistoryReader {

    PuchaseHistory getPuchaseHistoryBy(long itemId);

    BigDecimal getTotalPrice(Group group, PuchaseHistoryCommand.GetPuchasesRequest command);
    Page<PuchaseHistory> getPurchaseHistories(Group group, LocalDate selectedDate, int page, int size);

     SelectedMonthStatsDto getMonthlyPurchaseStats(long groupId, LocalDate selectedDate);

    List<MonthlyAmountDto> getPastFiveMonthsAmounts(long groupId, LocalDate startDate, LocalDate endDate);

     List<PuchaseHistory> getTop5RecentPurchaseHistories(long groupId, LocalDate selectedDate, Pageable pageable);






}

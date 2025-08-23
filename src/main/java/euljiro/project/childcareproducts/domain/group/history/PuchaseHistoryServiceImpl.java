package euljiro.project.childcareproducts.domain.group.history;

import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PuchaseHistoryServiceImpl implements PuchaseHistoryService{

    private final PuchaseHistoryStore puchaseHistoryStore;

    private final PuchaseHistoryReader puchaseHistoryReader;

    private final GroupReader groupReader;

    @Override
    public void addPurchaseHistory(ItemProductCommand.ConfirmProductRequest command) {
        PuchaseHistory initHistory = command.toHistoryEntity();
        puchaseHistoryStore.store(initHistory);
    }

    @Override
    public void deletePurchaseHistory(long itemId) {
        PuchaseHistory puchaseHistory = puchaseHistoryReader.getPuchaseHistoryBy(itemId);

        puchaseHistory.cancel();

        puchaseHistoryStore.store(puchaseHistory);
    }

    @Override
    public PuchaseHistoryInfo.GetPurchaseHistoriesResponse getPurchaseHistories(Group group, YearMonth selectedMonth, int page, int size) {

        Page<PuchaseHistory> purchaseHistories = puchaseHistoryReader.getPurchaseHistories(group, selectedMonth, page, size);

        return new PuchaseHistoryInfo.GetPurchaseHistoriesResponse(purchaseHistories);

    }

    @Override
    public PuchaseHistoryInfo.GetMainResponse getMainInfo(long groupId, YearMonth selectedMonth) {

        // 해당월 통계데이터
        SelectedMonthStatsDto monthlyPurchaseStats = puchaseHistoryReader.getMonthlyPurchaseStats(groupId, selectedMonth);

        // 월통합 데이터
        List<MonthlyAmountDto> pastFiveMonthsAmounts = puchaseHistoryReader.getPastFiveMonthsAmounts(groupId, selectedMonth.minusMonths(5).atDay(1), selectedMonth.atDay(1));

        //최근 구매이력 5개
        List<PuchaseHistory> top5RecentPurchaseHistories = puchaseHistoryReader.getTop5RecentPurchaseHistories(groupId, selectedMonth, PageRequest.of(0, 5));

        return new PuchaseHistoryInfo.GetMainResponse(monthlyPurchaseStats, pastFiveMonthsAmounts, top5RecentPurchaseHistories);
    }
}

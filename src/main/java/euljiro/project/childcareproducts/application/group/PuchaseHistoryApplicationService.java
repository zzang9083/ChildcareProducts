package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PuchaseHistoryApplicationService {

    private final GroupService groupService;
    private final PuchaseHistoryService puchaseHistoryService;

    public PuchaseHistoryInfo.GetMainResponse getPurchaseHistoryMain(String groupToken, YearMonth selectedMonth){
        Group group = groupService.getGroupBy(groupToken);

        return puchaseHistoryService.getMainInfo(group.getId(), selectedMonth);
    }

    public PuchaseHistoryInfo.GetPurchaseHistoriesResponse
    getPurchaseHistories(String groupToken, YearMonth selectedMonth, int page, int size) {

        Group group = groupService.getGroupBy(groupToken);


        return puchaseHistoryService.getPurchaseHistories(group, selectedMonth, page, size);
    }
}

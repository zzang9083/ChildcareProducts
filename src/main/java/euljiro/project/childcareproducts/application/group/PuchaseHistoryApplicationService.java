package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PuchaseHistoryApplicationService {

    private final GroupService groupService;
    private final PuchaseHistoryService puchaseHistoryService;

    public PuchaseHistoryInfo.GetMainResponse getPurchaseHistoryMain(String groupToken, LocalDate selectedDate){
        Group group = groupService.getGroupBy(groupToken);

        return puchaseHistoryService.getMainInfo(group.getId(), selectedDate);
    }

    public PuchaseHistoryInfo.GetPurchaseHistoriesResponse
    getPurchaseHistories(String groupToken, LocalDate selectedDate, int page, int size) {

        Group group = groupService.getGroupBy(groupToken);


        return puchaseHistoryService.getPurchaseHistories(group, selectedDate, page, size);
    }
}

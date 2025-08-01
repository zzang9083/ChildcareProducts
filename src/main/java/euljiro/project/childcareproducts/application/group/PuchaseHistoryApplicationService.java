package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PuchaseHistoryApplicationService {

    private final PuchaseHistoryService puchaseHistoryService;

//    public PuchaseHistoryInfo.GetDashBoardResponse getPurchaseHistoryDashBoard(groupToken){
//
//    }

    public PuchaseHistoryInfo.GetPuchasesResponse getPurchases(PuchaseHistoryCommand.GetPuchasesRequest command, int page, int size) {

        return puchaseHistoryService.getPurchases(command, page, size);
    }
}

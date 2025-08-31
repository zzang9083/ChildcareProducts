package euljiro.project.childcareproducts.api.group;

import euljiro.project.childcareproducts.api.group.dto.PuchaseHistoryDto;
import euljiro.project.childcareproducts.application.group.PuchaseHistoryApplicationService;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupPuchaseHistoryController {

    private final PuchaseHistoryApplicationService puchaseHistoryApplicationService;

    //private final PuchaseHistoryMapper puchaseHistoryMapper;


    @Operation(summary = "가계부 메인", description = "가계부 대쉬보드 처리")
    @GetMapping("/purchase-histories/summary")
    public CommonResponse getPurchaseHistoryMain(
            @PathVariable String groupToken,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectedMonth)
    {
        log.debug("GroupPuchaseHistoryController.getPurchaseHistoryMain start:: groupToken : {}", groupToken);

        if (selectedMonth == null) {
            selectedMonth = YearMonth.now().minusMonths(1); // 기본: 지난 달
        }

        // 서비스 호출
        PuchaseHistoryInfo.GetMainResponse response = puchaseHistoryApplicationService.getPurchaseHistoryMain(groupToken, selectedMonth);

        log.debug("GroupPuchaseHistoryController.getPurchaseHistoryMain end:: GetMainResponse : {}", response);


        return CommonResponse.success(new PuchaseHistoryDto.GetPurchaseHistoryMain(response));
     }

    @Operation(summary = "구매이력리스트조회", description = "구매한이력리스트를 조회한다.")
    @GetMapping("/purchase-histories")
    public CommonResponse getPurchaseHistories(
            @PathVariable String groupToken,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectedMonth,
            @RequestParam(defaultValue = "0") int page,                                //현재 페이지
            @RequestParam(defaultValue = "5") int size                                 //크기
            )
    {
        if (selectedMonth == null) {
            selectedMonth = YearMonth.now().minusMonths(1); // 기본: 지난 달
        }

        // 서비스 호출
        PuchaseHistoryInfo.GetPurchaseHistoriesResponse response
                = puchaseHistoryApplicationService.getPurchaseHistories(groupToken, selectedMonth, page, size);

        return CommonResponse.success(new PuchaseHistoryDto.GetPurchaseHistoriesResponse(response));
        //return CommonResponse.success(puchaseHistoryMapper.of(response));
    }

    @Operation(summary = "구매이력상세조회", description = "구매이력의 상세를 조회한다.")
    @GetMapping("/purchase-histories/{purchaseHistoryId}")
    public CommonResponse getPurchaseHistory(@PathVariable String groupToken,
                                             @PathVariable long purchaseHistoryId){

        // 서비스 호출
        PuchaseHistoryInfo.Main response
                = puchaseHistoryApplicationService.getPurchaseHistory(purchaseHistoryId);



        return CommonResponse.success(PuchaseHistoryDto.PuchaseHistory.from(response));
    }

}

package euljiro.project.childcareproducts.api.group;

import euljiro.project.childcareproducts.api.group.dto.PuchaseHistoryDto;
import euljiro.project.childcareproducts.application.group.PuchaseHistoryApplicationService;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryCommand;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupPuchaseHistoryController {

    private final PuchaseHistoryApplicationService puchaseHistoryApplicationService;

    //private final PuchaseHistoryMapper puchaseHistoryMapper;


    @Operation(summary = "가계부 메인", description = "가계부 대쉬보드 처리")
    @GetMapping("/purchaseHistory")
    public CommonResponse getPurchaseHistoryMain(
            @PathVariable String groupToken,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") LocalDate selectedDate)
    {
        // 서비스 호출
        PuchaseHistoryInfo.GetMainResponse response = puchaseHistoryApplicationService.getPurchaseHistoryMain(groupToken, selectedDate);

        return CommonResponse.success(new PuchaseHistoryDto.GetPurchaseHistoryMain(response));
        //return CommonResponse.success(puchaseHistoryMapper.of(response));
    }

    @Operation(summary = "구매이력리스트조회", description = "구매한이력리스트를 조회한다.")
    @GetMapping("/purchases")
    public CommonResponse getPurchases(
            @PathVariable String groupToken,
            @RequestParam(required = false) Item.Category category,                                         // 필터 조건
            @RequestParam(required = false) Product.PurchaseRoute purchaseRoute,                            // 필터 조건
            @RequestParam(required = false) Product.ProductStatus productStatus,                            // 필터 조건
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,    // 필터 조건
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,      // 필터 조건
            @RequestParam(defaultValue = "0") int page,                                                     // 현재 페이지
            @RequestParam(defaultValue = "5") int size)                                                     // 크기
    {
        var request =
                        new PuchaseHistoryCommand.GetPuchasesRequest(groupToken, category, purchaseRoute
                                , productStatus, startDate, endDate);


        // 서비스 호출
        PuchaseHistoryInfo.GetPuchasesResponse response
                = puchaseHistoryApplicationService.getPurchases(request, page, size);

        return CommonResponse.success(new PuchaseHistoryDto.GetPuchasesResponse(response));
        //return CommonResponse.success(puchaseHistoryMapper.of(response));
    }


}

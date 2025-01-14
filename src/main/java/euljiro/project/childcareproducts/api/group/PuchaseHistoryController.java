package euljiro.project.childcareproducts.api.group;

import euljiro.project.childcareproducts.api.group.dto.CardDto;
import euljiro.project.childcareproducts.api.group.dto.GroupDto;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class PuchaseHistoryController {

    private final PuchaseHistoryApplicationService puchaseHistoryApplicationService;

    //private final PuchaseHistoryMapper puchaseHistoryMapper;



    @Operation(summary = "구매이력리스트조회", description = "구매한이력리스트를 조회한다.")
    @GetMapping("/purchases")
    public CommonResponse getPurchases(
            @PathVariable String groupToken,
            @RequestParam(required = false) Item.Category category,                 // 필터 조건
            @RequestParam(required = false) Product.PurchaseRoute purchaseRoute,    // 필터 조건
            @RequestParam(required = false) Product.ProductStatus productStatus,    // 필터 조건
            @RequestParam(required = false) LocalDate startDate,                    // 필터 조건
            @RequestParam(required = false) LocalDate endDate                       // 필터 조건
    ) {
        var request =
                        new PuchaseHistoryCommand.GetPuchasesRequest(groupToken, category, purchaseRoute
                                , productStatus, startDate, endDate);


        // 서비스 호출
        PuchaseHistoryInfo.GetPuchasesResponse response
                = puchaseHistoryApplicationService.getPurchases(request);

        return CommonResponse.success(new PuchaseHistoryDto.GetPuchasesResponse(response));
        //return CommonResponse.success(puchaseHistoryMapper.of(response));
    }


}
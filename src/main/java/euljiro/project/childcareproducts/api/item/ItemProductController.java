package euljiro.project.childcareproducts.api.item;


import euljiro.project.childcareproducts.api.item.dto.ItemProductDto;
import euljiro.project.childcareproducts.api.item.dto.ItemProductDtoMapper;
import euljiro.project.childcareproducts.application.item.ItemProductService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item/{itemToken}")
public class ItemProductController {

    private final ItemProductService itemProductService;

    private final ItemProductDtoMapper itemProductDtoMapper;

    @GetMapping("/datails")
    public CommonResponse getItemAndProducts(@PathVariable String itemToken)
    {
        var itemProductInfo
                = itemProductService.getItemAndProduct(itemToken);

        var response= itemProductDtoMapper.of(itemProductInfo);

        return CommonResponse.success(response);
    }


    @PostMapping("/product")
    public CommonResponse registerProduct(@PathVariable String itemToken, @RequestBody @Valid ItemProductDto.RegisterProductRequest request) {
        var command = request.toCommand(itemToken);

        var productToken
                = itemProductService.registerProduct(command);

        return CommonResponse.success(productToken);
    }

    @PostMapping("/confirm-purchase")
    public CommonResponse confirmPurchase(@PathVariable String itemToken, @RequestBody @Valid ItemProductDto.ConfirmProductRequest request) {
        var command = request.toCommand(itemToken);

        itemProductService.confirmPurchase(command);

        return CommonResponse.success(HttpStatus.OK);
    }
}

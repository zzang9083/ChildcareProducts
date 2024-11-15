package euljiro.project.childcareproducts.api.item;


import euljiro.project.childcareproducts.api.complex.dto.GroupDto;
import euljiro.project.childcareproducts.api.item.dto.ItemDto;
import euljiro.project.childcareproducts.api.item.dto.ItemDtoMapper;
import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import euljiro.project.childcareproducts.application.item.ItemApplicationService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.domain.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemApplicationService itemApplicationService;

    private final ItemDtoMapper itemDtoMapper;

    @PostMapping("")
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        var command = request.toCommand();

        var itemToken
                = itemApplicationService.registerItem(command);

        return CommonResponse.success(itemToken);
    }



    @GetMapping("/{itemToken}")
    public CommonResponse getItem(@PathVariable String itemToken) {
        var itemInfo
                = itemApplicationService.getItem(itemToken);

        var response= itemDtoMapper.of(itemInfo);

        return CommonResponse.success(response);
    }

    @PutMapping("/{itemToken}")
    public CommonResponse updateItem(@PathVariable String inputItemToken
            , @RequestBody @Valid ItemDto.UpdateItemRequest request) {
        var command = request.toCommand(inputItemToken);

        itemApplicationService.updateItem(command);

        return CommonResponse.success(HttpStatus.OK);
    }

    @PutMapping("/{itemToken}/status")
    public CommonResponse changeStatus(@PathVariable String inputItemToken
            , @RequestBody @Valid ItemDto.ChangeStatusRequest request) {
        var command = request.toCommand(inputItemToken);

        itemApplicationService.changeStatus(command);

        return CommonResponse.success(HttpStatus.OK);
    }

    @DeleteMapping("/{orderToken}")
    public CommonResponse deleteItem(@PathVariable String orderToken) {
        itemApplicationService.deleteItem(orderToken);
        return CommonResponse.success(HttpStatus.OK);
    }







}

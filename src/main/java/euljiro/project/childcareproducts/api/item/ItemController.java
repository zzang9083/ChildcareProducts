package euljiro.project.childcareproducts.api.item;


import euljiro.project.childcareproducts.api.item.dto.ItemDto;
import euljiro.project.childcareproducts.api.item.dto.ItemDtoMapper;
import euljiro.project.childcareproducts.application.item.ItemApplicationService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
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


    @GetMapping("/{itemToken}")
    public CommonResponse getItem(@PathVariable String itemToken) {
        var itemInfo
                = itemApplicationService.getItem(itemToken);

        var response= itemDtoMapper.of(itemInfo);

        return CommonResponse.success(response);
    }

    @PutMapping("/{itemToken}")
    public CommonResponse updateItem(@PathVariable String itemToken
            , @RequestBody @Valid ItemDto.UpdateItemRequest request) {
        var command = request.toCommand(itemToken);

        itemApplicationService.updateItem(command);

        return CommonResponse.success(HttpStatus.OK);
    }

    @PutMapping("/{itemToken}/status")
    public CommonResponse changeStatus(@PathVariable String itemToken
            , @RequestBody @Valid ItemDto.ChangeStatusRequest request) {
        log.debug("ItemController.changeStatus start:: input : {}", request.getStatus());

        var command = request.toCommand(itemToken);

        itemApplicationService.changeStatus(command);

        return CommonResponse.success(HttpStatus.OK);
    }

    @DeleteMapping("/{itemToken}")
    public CommonResponse deleteItem(@PathVariable String itemToken) {
        itemApplicationService.deleteItem(itemToken);
        return CommonResponse.success(HttpStatus.OK);
    }







}

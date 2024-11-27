package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.GroupItemDto;
import euljiro.project.childcareproducts.api.group.dto.GroupItemDtoMapper;
import euljiro.project.childcareproducts.api.item.dto.ItemDto;
import euljiro.project.childcareproducts.api.item.dto.ItemProductDto;
import euljiro.project.childcareproducts.application.group.GroupItemService;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupItemController {

    private final GroupItemService groupItemService;

    private final GroupItemDtoMapper groupItemDtoMapper;


    @PostMapping("/item")
    public CommonResponse registerItem(@PathVariable String groupToken, @RequestBody @Valid GroupItemDto.RegisterItemRequest request) {
        var command = request.toCommand(groupToken);

        var itemToken
                = groupItemService.registerItem(command);

        return CommonResponse.success(new GroupItemDto.RegisterItemResponse(itemToken));
    }
    @GetMapping("/items")
    public CommonResponse getItems(@PathVariable String groupToken) {

        var items = groupItemService.getItems(groupToken);

        return CommonResponse.success(new GroupItemDto.GetItemsResponse(items));
    }


}

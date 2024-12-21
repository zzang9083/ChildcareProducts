package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.GroupDto;
//import euljiro.project.childcareproducts.api.group.dto.GroupDtoMapper;
import euljiro.project.childcareproducts.application.group.GroupItemService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupController {

    private final GroupItemService groupItemService;

    //private final GroupDtoMapper groupDtoMapper;


    @PostMapping("/item")
    public CommonResponse registerItem(@PathVariable String groupToken, @RequestBody @Valid GroupDto.RegisterItemRequest request) {
        var command = request.toCommand(groupToken);

        var itemToken
                = groupItemService.registerItem(command);

        return CommonResponse.success(new GroupDto.RegisterItemResponse(itemToken));
    }
    @GetMapping("/items")
    public CommonResponse getItems(@PathVariable String groupToken) {

        var items = groupItemService.getItems(groupToken);

        return CommonResponse.success(new GroupDto.GetItemsResponse(items));
    }


}

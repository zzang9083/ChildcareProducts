package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.GroupDto;
//import euljiro.project.childcareproducts.api.group.dto.GroupDtoMapper;
import euljiro.project.childcareproducts.application.group.GroupItemService;
import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupController {

    private final GroupItemService groupItemService;

    //private final GroupDtoMapper groupDtoMapper;


    @PostMapping("/item")
    public CommonResponse registerItem(@PathVariable String groupToken,
                                       @RequestParam String childToken,
                                       @RequestBody @Valid GroupDto.RegisterItemRequest request) {
        var command = request.toCommand(groupToken, childToken);

        var itemToken
                = groupItemService.registerItem(command);

        return CommonResponse.success(new GroupDto.RegisterItemResponse(itemToken));
    }
    @GetMapping("/items")
    public CommonResponse getItems(@PathVariable String groupToken,
                                   @RequestParam String childToken,
                                   @RequestParam(defaultValue = "0") int page,     // 현재 페이지
                                   @RequestParam(defaultValue = "5") int size)    // 크기
    {
        var req = new GroupItemCommand.GetItemsRequest(groupToken, childToken);


        var items = groupItemService.getItems(req, page, size);

        return CommonResponse.success(new GroupDto.GetItemsResponse(items));
    }


}

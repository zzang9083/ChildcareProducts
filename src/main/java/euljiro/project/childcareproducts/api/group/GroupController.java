package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.GroupDto;
import euljiro.project.childcareproducts.application.group.GroupItemService;
import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupController {

    private final GroupItemService groupItemService;



    @PostMapping("/item")
    public CommonResponse registerItem(@PathVariable String groupToken,
                                       @RequestParam String childToken,
                                       Authentication authentication,
                                       @RequestBody @Valid GroupDto.RegisterItemRequest request) {
        String userKey = (String) authentication.getPrincipal();

        var command = request.toCommand(groupToken, childToken, userKey);
        log.debug("GroupController.registerItem start:: input : {}", request);

        var itemToken
                = groupItemService.registerItem(command);

        log.debug("GroupController.registerItem end:: itemToken : {}", itemToken);
        return CommonResponse.success(new GroupDto.RegisterItemResponse(itemToken));
    }
    @GetMapping("/items")
    public CommonResponse getItems(@PathVariable String groupToken,
                                   @RequestParam String childToken,
                                   @RequestParam(required = false) Item.Status status,
                                   @RequestParam(defaultValue = "0") int page,     // 현재 페이지
                                   @RequestParam(defaultValue = "5") int size)    // 크기
    {
        var req = new GroupItemCommand.GetItemsRequest(groupToken, childToken, status);

        var items = groupItemService.getItems(req, page, size);

        return CommonResponse.success(new GroupDto.GetItemsResponse(items));
    }




}

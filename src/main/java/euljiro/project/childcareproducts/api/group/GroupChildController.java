package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.ChildDto;
import euljiro.project.childcareproducts.application.child.ChildApplicationService;
import euljiro.project.childcareproducts.application.group.GroupChildApplicationService;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupChildController {
    private final GroupChildApplicationService groupchildService;

    private final ChildApplicationService childApplicationService;


    @PostMapping("/child")
    public CommonResponse registerChild(@PathVariable String groupToken, @RequestBody @Valid ChildDto.RegisterChildRequest request) {
        log.debug("GroupChildController.registerChild start:: request : {}", request);

        var registerChildRequest = request.toChildCommand(groupToken);

        String childToken = groupchildService.registerChildByGroup(registerChildRequest);

        log.debug("GroupChildController.registerChild end:: childToken : {}", childToken);

        return CommonResponse.success(new ChildDto.RegisterChildResponse(childToken));
    }

    @GetMapping("/children")
    public CommonResponse getChildren(@PathVariable String groupToken) {
        log.debug("GroupChildController.getChilds start:: request : {}", groupToken);

        GroupChildInfo.GetChildrenResponse children = groupchildService.getChildren(groupToken);

        log.debug("GroupChildController.getChilds end:: response : {}", children);

        return CommonResponse.success(new ChildDto.GetChildrenResponse(children));
    }

    @PutMapping("/selected-child")
    public void changeSelectedChild(@PathVariable String groupToken, @Valid @RequestBody ChildDto.ChangeSelectedChildRequest request) {
        groupchildService.changeSelectedChild(groupToken, request.getChildToken());
    }

    @PatchMapping("/child")
    public void deleteChild(@PathVariable String groupToken, @PathVariable String childToken ) {
        childApplicationService.deleteChild(childToken);
    }

    //GETCHILD

    //MODIFYCHILD




}

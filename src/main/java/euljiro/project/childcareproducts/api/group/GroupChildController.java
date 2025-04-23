package euljiro.project.childcareproducts.api.group;


import euljiro.project.childcareproducts.api.group.dto.CardDtoMapper;
import euljiro.project.childcareproducts.api.group.dto.ChildDto;
import euljiro.project.childcareproducts.application.group.GroupChildApplicationService;
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

    private final CardDtoMapper cardDtoMapper;



    @PostMapping("/child")
    public CommonResponse registerChild(@PathVariable String groupToken, @RequestBody @Valid ChildDto.RegisterChildRequest request) {
        log.debug("GroupChildController.registerChild start:: request : {}", request);


        var registerChildRequest = request.toChildCommand(groupToken);

        String childToken = groupchildService.registerChildByGroup(registerChildRequest);

        log.debug("GroupChildController.registerChild end:: childToken : {}", childToken);

        return CommonResponse.success(new ChildDto.RegisterChildResponse(childToken));
    }

    @PutMapping("/selected-child")
    public void changeSelectedChild(@PathVariable String groupToken, @RequestBody @Valid String childToken) {
        groupchildService.changeSelectedChild(groupToken, childToken);
    }

//    @PatchMapping("/child/{childToken}")
//    public CommonResponse deleteChild(@PathVariable String groupToken, @PathVariable String childToken, @RequestBody @Valid CardDto.RegisterCardRequest request) {
//
//        var registerCardRequest = request.toCardCommand(groupToken);
//
//        String cardToken = groupCardApplicationService.registerCard(registerCardRequest);
//
//        return CommonResponse.success(new CardDto.RegisterCardResponse(cardToken));
//    }

}

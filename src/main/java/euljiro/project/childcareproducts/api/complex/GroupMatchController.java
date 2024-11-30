package euljiro.project.childcareproducts.api.complex;

import euljiro.project.childcareproducts.application.complex.GroupMatchService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.api.complex.dto.GroupDto;
import euljiro.project.childcareproducts.api.complex.dto.GroupDtoMapper;
import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupMatchController {

    private final GroupMatchService groupMatchService;

    private final GroupDtoMapper groupDtoMapper;

    @PostMapping("")
    public CommonResponse matchGroup(@RequestBody @Valid GroupDto.MatchGroupRequest request) {
        var command = request.toCommand();

        GroupInfo.MatchGroupResponse matchGroupResponse
                                = groupMatchService.matchGroup(command);

        log.info("***** GroupMatchController.matchGroup end *****");

        var response  = groupDtoMapper.of(matchGroupResponse);

        return CommonResponse.success(response);
    }
}

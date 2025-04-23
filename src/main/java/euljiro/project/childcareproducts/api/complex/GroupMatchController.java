package euljiro.project.childcareproducts.api.complex;

import euljiro.project.childcareproducts.api.complex.dto.GroupMatchDto;
import euljiro.project.childcareproducts.api.complex.dto.GroupMatchDtoMapper;
import euljiro.project.childcareproducts.application.complex.GroupMatchService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
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

    private final GroupMatchDtoMapper groupMatchDtoMapper;

    @PostMapping("")
    public CommonResponse matchGroup(@RequestBody @Valid GroupMatchDto.MatchGroupRequest request) {
        var command = request.toCommand();

        log.debug("***** GroupMatchController.matchGroup start req: {}", request);


        GroupMatchInfo.MatchGroupResponse matchGroupResponse
                                = groupMatchService.matchGroup(command);

        var response  = groupMatchDtoMapper.of(matchGroupResponse);

        log.debug("***** GroupMatchController.matchGroup End res: {}", response);

        return CommonResponse.success(response);
    }
}

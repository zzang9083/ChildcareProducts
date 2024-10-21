package euljiro.project.childcareproducts.api.user;

import euljiro.project.childcareproducts.application.user.GroupMatchService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.application.item.dto.GroupDto;
import euljiro.project.childcareproducts.application.item.dto.GroupDtoMapper;
import euljiro.project.childcareproducts.domain.group.GroupInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupMatchService groupMatchService;

    private final GroupDtoMapper groupDtoMapper;

    @Operation(summary = "사용자 그룹매칭", description = "그룹을 매칭한다.")
    @PostMapping("/match")
    public CommonResponse matchGroup(@RequestBody @Valid GroupDto.MatchGroupRequest request) {
        var command = request.toCommand();

        GroupInfo.MatchGroupResponse matchGroupResponse
                                = groupMatchService.matchGroup(command);

        var response  = groupDtoMapper.of(matchGroupResponse);

        return CommonResponse.success(response);
    }
}

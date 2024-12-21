package euljiro.project.childcareproducts.api.user;

import euljiro.project.childcareproducts.api.user.dto.UserDto;
import euljiro.project.childcareproducts.api.user.dto.UserDtoMapper;
import euljiro.project.childcareproducts.application.child.ChildApplicationService;
import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.application.user.UserApplicationService;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserApplicationService userApplicationService;

    private final ChildApplicationService childApplicationService;

    private final UserDtoMapper userDtoMapper;

    @PutMapping("/{userKey}")
    public CommonResponse registerUserInfo(@PathVariable String userKey, @RequestBody @Valid UserDto.UserInfoRegisterRequest request) {

        log.info("request UserKey: " + userKey);
        var registerUserInfoRequest = request.toUserCommand(userKey);

        UserInfo.UserInfoRegisterResponse userInfoRegisterResponse
                = userApplicationService.registerUserInfo(registerUserInfoRequest);

        var response = userDtoMapper.of(userInfoRegisterResponse);

        return CommonResponse.success(response);
    }

    @PostMapping("/{userKey}/child")
    public CommonResponse registerChild(@PathVariable String userKey, @RequestBody @Valid UserDto.ChildRegisterRequest request) {

        log.info("request UserKey: " + userKey);
        var registerChildRequest = request.toChildCommand(userKey);

        ChildInfo.ChildRegisterResponse childRegisterResponse
                = childApplicationService.registerChild(registerChildRequest);

        var response = userDtoMapper.of(childRegisterResponse);

        return CommonResponse.success(response);
    }

}

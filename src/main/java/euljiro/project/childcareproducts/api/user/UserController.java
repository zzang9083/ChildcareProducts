package euljiro.project.childcareproducts.api.user;

import euljiro.project.childcareproducts.api.user.dto.UserDto;
import euljiro.project.childcareproducts.api.user.dto.UserDtoMapper;
import euljiro.project.childcareproducts.application.child.ChildApplicationService;
import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.application.user.UserApplicationService;
import euljiro.project.childcareproducts.application.user.UserGroupService;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/{userKey}")
public class UserController {

    private final UserApplicationService userApplicationService;

    private final UserGroupService userGroupService;

    private final ChildApplicationService childApplicationService;

    private final UserDtoMapper userDtoMapper;

    @PutMapping("")
    public CommonResponse registerUserInfo(@PathVariable String userKey, @RequestBody @Valid UserDto.UserInfoRegisterRequest request) {

        log.info("request UserKey: " + userKey);
        var registerUserInfoRequest = request.toUserCommand(userKey);

        UserInfo.UserInfoRegisterResponse userInfoRegisterResponse
                = userApplicationService.registerUserInfo(registerUserInfoRequest);

        var response = userDtoMapper.of(userInfoRegisterResponse);

        return CommonResponse.success(response);
    }

    @PutMapping("/withdraw")
    public CommonResponse withdrawUser(@PathVariable String userKey) {
        log.trace("LoginController.withdrawUser start");
        log.info("request UserKey: " + userKey);

        userGroupService.withdrawUser(userKey);

        return CommonResponse.success(HttpStatus.OK);
    }


    @PostMapping("/child")
    public CommonResponse registerChild(@PathVariable String userKey, @RequestBody @Valid UserDto.ChildRegisterRequest request) {

        log.info("request UserKey: " + userKey);
        var registerChildRequest = request.toChildCommand(userKey);

        ChildInfo.ChildRegisterResponse childRegisterResponse
                = childApplicationService.registerChild(registerChildRequest);

        var response = userDtoMapper.of(childRegisterResponse);

        return CommonResponse.success(response);
    }

}

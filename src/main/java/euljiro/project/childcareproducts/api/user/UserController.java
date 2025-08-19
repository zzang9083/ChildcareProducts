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
        log.debug("UserController.registerUserInfo start:: input : {}", request);

        var registerUserInfoRequest = request.toUserCommand(userKey);

        UserInfo.UserInfoRegisterResponse userInfoRegisterResponse
                = userApplicationService.registerUserInfo(registerUserInfoRequest);

        var response = userDtoMapper.of(userInfoRegisterResponse);
        log.debug("LoginController.registerUserInfo end:: output : {}", response);

        return CommonResponse.success(response);
    }

    @PutMapping("/withdraw")
    public CommonResponse withdrawUser(@PathVariable String userKey) {
        log.debug("UserController.registerUserInfo start:: input : {}", userKey);

        userGroupService.withdrawUser(userKey);

        log.debug("LoginController.registerUserInfo end");
        return CommonResponse.success(HttpStatus.OK);
    }


    @PostMapping("/child")
    public CommonResponse registerChild(@PathVariable String userKey, @RequestBody @Valid UserDto.ChildRegisterRequest request) {
        log.debug("UserController.registerChild start:: userKey : {} / request vo : {}", userKey, request);

        var registerChildRequest = request.toChildCommand(userKey);

        ChildInfo.ChildRegisterResponse childRegisterResponse
                = childApplicationService.registerChildByUser(registerChildRequest);

        var response = userDtoMapper.of(childRegisterResponse);
        log.debug("LoginController.registerChild end:: output : {}", response);

        return CommonResponse.success(response);
    }

    @PutMapping("/selected-child")
    public CommonResponse changeSelectedChild(@PathVariable String userKey, @Valid @RequestBody UserDto.ChangeSelectedChildRequest request) {
        long selectedChildId = userApplicationService.changeSelectedChild(userKey, request.getChildToken());

        return CommonResponse.success(selectedChildId);
    }

}

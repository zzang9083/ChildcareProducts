package euljiro.project.childcareproducts.api.complex;


import euljiro.project.childcareproducts.api.complex.dto.LoginDtoMapper;
import euljiro.project.childcareproducts.api.complex.dto.UserInfoRegisterDto;
import euljiro.project.childcareproducts.api.complex.dto.UserInfoRegisterDtoMapper;
import euljiro.project.childcareproducts.application.complex.UserInfoRegisterService;
import euljiro.project.childcareproducts.application.complex.dto.UserRegisterInfo;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class UserInfoRegisterController {

    private final UserInfoRegisterService userInfoRegisterService;


    private final UserInfoRegisterDtoMapper registerDtoMapper;

    @PutMapping("")
    public CommonResponse registerUserInfo(@RequestBody @Valid UserInfoRegisterDto request) {

        var registerUserInfoRequest = request.toUserCommand();
        var registerChildInfoRequest = request.toChildCommand();

        UserRegisterInfo.UserInfoRegisterResponse userInfoRegisterResponse
                = userInfoRegisterService.registerUserInfo(registerUserInfoRequest, registerChildInfoRequest);

        var response = registerDtoMapper.of(userInfoRegisterResponse);

        return CommonResponse.success(response);
    }
}

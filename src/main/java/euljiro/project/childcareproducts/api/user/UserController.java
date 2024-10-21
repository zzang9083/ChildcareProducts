package euljiro.project.childcareproducts.api.user;


import euljiro.project.childcareproducts.application.item.dto.UserDto;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("")
    public CommonResponse registerUserInfo(@RequestBody @Valid UserDto.RegisterRequest request) {

//        var loginResponse = loginService.login(request);
//
//        var response = loginDtoMapper.of(loginResponse);

        return CommonResponse.success(response);
    }
}

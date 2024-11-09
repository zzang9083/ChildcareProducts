package euljiro.project.childcareproducts.api.complex;

import euljiro.project.childcareproducts.api.complex.dto.LoginDto;
import euljiro.project.childcareproducts.api.complex.dto.LoginDtoMapper;
import euljiro.project.childcareproducts.application.complex.LoginService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCodeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {


    private final LoginService loginService;

    private final LoginDtoMapper loginDtoMapper;

    @Operation(summary = "연결테스트", description = "연결 정상 테스트")
    @GetMapping("/connectionTest")
    public CommonResponse test() {
        return CommonResponse.success("OK");
    }


    @Operation(summary = "로그인", description = "소셜계정으로 로그인")
    @PostMapping("")
    public CommonResponse login(@RequestBody @Valid LoginDto.LoginRequest request) {

        String accessToken = request.getAccessToken();

        var loginResponse = loginService.login(accessToken);

        var response = loginDtoMapper.of(loginResponse);

        return CommonResponse.success(response);
    }

    @Operation(summary = "재로그인", description = "리프레시토큰을 통해 다시 로그인")
    @PostMapping("/retry")
    public CommonResponse reissueToken(@RequestBody @Valid LoginDto.ReissueRequest request) {

        String refreshToken = request.getRefreshToken();

        var reissueResponse = loginService.reissueToken(refreshToken);

        var response = loginDtoMapper.of(reissueResponse);

        return CommonResponse.success(response);
    }




//    @Operation(summary = "사용자 가입", description = "사용자를 가입 처리한다.")
//    @PostMapping("/join")
//    public CommonResponse joinUser(@RequestBody @Valid UserDto.JoinUserRequest request) {
//        var command = request.toCommand();
//        var userInfo = userFacade.registerUser(command);
//        var response = new UserDto.JoinUserResponse(userInfo);
//
//        return CommonResponse.success(response);
//    }

//    @Operation(summary = "사용자 조회", description = "사용자의 정보를 조회한다.")
//    @GetMapping("/{userToken}")
//    public CommonResponse getUserInfo(@PathVariable String userToken) {
//        var userInfo = userFacade.getUserInfo(userToken);
//        var response = userDtoMapper.of(userInfo);
//
//        return CommonResponse.success(response);
//    }

//    @Operation(summary = "사용자 탈퇴", description = "사용자를 탈퇴처리한다.")
//    @PutMapping("/{userToken}")
//    public CommonResponse withdrawUser(@PathVariable String userToken) {
//        var userInfo = userFacade.getUserInfo(userToken);
//        var response = userDtoMapper.of(userInfo);
//
//        return CommonResponse.success(response);
//    }








}
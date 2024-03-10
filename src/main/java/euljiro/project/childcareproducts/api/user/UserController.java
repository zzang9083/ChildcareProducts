package euljiro.project.childcareproducts.api.user;

import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.api.user.dto.UserDto;
import euljiro.project.childcareproducts.api.user.dto.UserDtoMapper;
import euljiro.project.childcareproducts.domain.user.UserCommand;
import euljiro.project.childcareproducts.domain.user.auth.AuthService;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final ShareCodeService shareCodeService;

    private final AuthService authService;


    private final UserDtoMapper userDtoMapper;

    @GetMapping("/connectionTest")
    public CommonResponse test() {
        return CommonResponse.success("OK");
    }



    @Operation(summary = "사용자 로그인", description = "사용자를 로그인을 처리한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 로그인", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패")
    })
    @PostMapping("/login")
    public CommonResponse login(@RequestBody @Valid UserDto.LoginRequest request) {

        String accessToken = request.getAccessToken();
        var loginResponse = authService.isSignUp(accessToken);

        var response = userDtoMapper.of(loginResponse);

        return CommonResponse.success(response);
    }

    @Operation(summary = "토큰 재발급", description = "토큰을 재발급")
    @PostMapping("/refresh")
    public CommonResponse reissueToken(@RequestBody @Valid UserDto.ReissueRequest request) {

        UserCommand.ReissueRequest reissueRequest = request.toCommand();
        var reissueResponse = authService.reissueToken(reissueRequest);

        var response = userDtoMapper.of(reissueResponse);

        return CommonResponse.success(response);
    }

    @Operation(summary = "사용자 공유코드 발급", description = "그룹매칭을 위한 공유코드를 발급받는다.")
    @PostMapping("/shareCode")
    public CommonResponse getShareCode(@RequestBody @Valid UserDto.GetShareCodeRequest request) {
        var userKey = request.getUserKey();
        var shareCode = shareCodeService.generateShareCode(userKey);

        var response  = userDtoMapper.of(shareCode);

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

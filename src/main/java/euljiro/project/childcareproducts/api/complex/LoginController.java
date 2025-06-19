package euljiro.project.childcareproducts.api.complex;

import euljiro.project.childcareproducts.api.complex.dto.LoginDto;
import euljiro.project.childcareproducts.api.complex.dto.LoginDtoMapper;
import euljiro.project.childcareproducts.application.complex.LoginService;
import euljiro.project.childcareproducts.application.complex.dto.LoginCommand;
import euljiro.project.childcareproducts.application.event.PushNotificationEvent;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.PushMessageType;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {


    private final LoginService loginService;

    private final LoginDtoMapper loginDtoMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Operation(summary = "연결테스트", description = "연결 정상 테스트")
    @GetMapping("/connectionTest")
    public CommonResponse test() {
        return CommonResponse.success("OK");
    }

    @Operation(summary = "연결테스트", description = "연결 정상 테스트")
    @GetMapping("/pushTest/{value}")
    public CommonResponse pushTest(@PathVariable String value) {
        log.debug("LoginController.pushTest start:: input : {}", value);


        if(value.equals("1")) {
            eventPublisher.publishEvent(
                    new PushNotificationEvent("3340063804", PushMessageType.MATCH_COMPLETE)
            );
        }
        else if(value.equals("2")) {
            eventPublisher.publishEvent(
                    new PushNotificationEvent("3383294830", PushMessageType.MATCH_COMPLETE)
            );
        }

        return CommonResponse.success("OK");
    }


//    @Operation(summary = "APPLE로그인", description = "애플계정 리다이렉트")
//    @PostMapping("/redirect/apple")
//    public CommonResponse callback(@RequestParam("code") String code) {
//        log.debug("LoginController.callback start:: code : {}", code);
//
//        loginService.redirectAppleLogin(code);
//
//        //var response = loginDtoMapper.of(loginResponse);
//        //log.debug("LoginController.login end:: output : {}", response);
//
//        return CommonResponse.success();
//    }


    @Operation(summary = "로그인", description = "소셜계정으로 로그인")
    @PostMapping("")
    public CommonResponse login(@RequestBody @Valid LoginDto.LoginRequest request) {
        log.debug("LoginController.login start:: input : {}", request);

        LoginCommand.LoginRequest command = request.toCommand();

        var loginResponse = loginService.login(command);

        var response = loginDtoMapper.of(loginResponse);

        log.debug("LoginController.login end:: output : {}", response);
        return CommonResponse.success(response);
    }

    @Operation(summary = "재로그인", description = "리프레시토큰을 통해 다시 로그인")
    @PostMapping("/retry")
    public CommonResponse reissueToken(@RequestBody @Valid LoginDto.ReissueRequest request) {
        log.debug("LoginController.reissueToken start:: input : {}", request);

        String refreshToken = request.getRefreshToken();

        var reissueResponse = loginService.reissueToken(refreshToken);

        var response = loginDtoMapper.of(reissueResponse);

        log.debug("LoginController.reissueToken end:: output : {}", response);
        return CommonResponse.success(response);
    }

    @Operation(summary = "대시보드조회", description = "로그인시 그룹의 대시보드 정보를 조회")
    @GetMapping("/dashboard/group/{groupToken}")
    public CommonResponse getDashBoardInfo(@PathVariable @Valid String groupToken) {
        log.debug("LoginController.getDashBoardInfo start:: input : {}", groupToken);

        var dashBoardInfo = loginService.getDashBoardInfo(groupToken);

        var response = loginDtoMapper.of(dashBoardInfo);

        log.debug("LoginController.getDashBoardInfo end:: output : {}", response);
        return CommonResponse.success(response);
    }


//    @Operation(summary = "사용자 탈퇴", description = "사용자를 탈퇴처리한다.")
//    @PutMapping("/{userToken}")
//    public CommonResponse withdrawUser(@PathVariable String userToken) {
//        var userInfo = userFacade.getUserInfo(userToken);
//        var response = userDtoMapper.of(userInfo);
//
//        return CommonResponse.success(response);
//    }


}

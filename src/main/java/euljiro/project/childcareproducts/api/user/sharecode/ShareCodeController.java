package euljiro.project.childcareproducts.api.user.sharecode;

import euljiro.project.childcareproducts.api.user.sharecode.dto.ShareCodeDto;
import euljiro.project.childcareproducts.api.user.sharecode.dto.ShareCodeDtoMapper;
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
@RequestMapping("/api/v1")
public class ShareCodeController {

    private final ShareCodeService shareCodeService;

    private final ShareCodeDtoMapper shareCodeDtoMapper;

    @Operation(summary = "사용자 공유코드 발급", description = "그룹매칭을 위한 공유코드를 발급받는다.")
    @PostMapping("/user/shareCode/{userKey}")
    public CommonResponse getShareCode(@PathVariable String userKey) {
        var shareCode = shareCodeService.generateShareCode(userKey);

        var response  = shareCodeDtoMapper.of(shareCode);

        return CommonResponse.success(response);
    }
}

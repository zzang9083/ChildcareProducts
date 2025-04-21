package euljiro.project.childcareproducts.api.group;

import euljiro.project.childcareproducts.api.group.dto.CardDto;
import euljiro.project.childcareproducts.api.group.dto.CardDtoMapper;
import euljiro.project.childcareproducts.application.group.GroupCardApplicationService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group/{groupToken}")
public class GroupCardController {

    private final GroupCardApplicationService groupCardApplicationService;

    private final CardDtoMapper cardDtoMapper;



    @PostMapping("/card")
    public CommonResponse registerCard(@PathVariable String groupToken, @RequestBody @Valid CardDto.RegisterCardRequest request) {

        var registerCardRequest = request.toCardCommand(groupToken);

        String cardToken = groupCardApplicationService.registerCard(registerCardRequest);

        return CommonResponse.success(new CardDto.RegisterCardResponse(cardToken));
    }

    @GetMapping("/cards")
    public CommonResponse getCards(@PathVariable String groupToken) {

        var cards = groupCardApplicationService.getCards(groupToken);

        return CommonResponse.success(new CardDto.GetCardsResponse(cards));
    }

    @PatchMapping("/card/{cardToken}/disable")
    public CommonResponse disableCard(@PathVariable String groupToken, @PathVariable String cardToken) {

        groupCardApplicationService.disableCard(cardToken);

        return CommonResponse.success(HttpStatus.OK);
    }

}

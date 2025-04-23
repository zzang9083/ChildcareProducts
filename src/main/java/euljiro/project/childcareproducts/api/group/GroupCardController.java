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

        log.debug("GroupCardController.registerCard start:: input : {}", request);

        var registerCardRequest = request.toCardCommand(groupToken);

        String cardToken = groupCardApplicationService.registerCard(registerCardRequest);

        log.debug("GroupCardController.registerCard end:: cardToken : {}", cardToken);

        return CommonResponse.success(new CardDto.RegisterCardResponse(cardToken));
    }

    @GetMapping("/cards")
    public CommonResponse getCards(@PathVariable String groupToken) {
        log.debug("GroupCardController.getCards start:: groupToken : {}", groupToken);

        var cards = groupCardApplicationService.getCards(groupToken);

        log.debug("GroupCardController.getCards end:: cards : {}", cards);

        return CommonResponse.success(new CardDto.GetCardsResponse(cards));
    }

    @PatchMapping("/card/{cardToken}/disable")
    public CommonResponse disableCard(@PathVariable String groupToken, @PathVariable String cardToken) {
        log.debug("GroupCardController.disableCard start:: groupToken : {} " +
                                                        ", cardToken : {}", groupToken, cardToken);

        groupCardApplicationService.disableCard(cardToken);

        log.debug("GroupCardController.disableCard end");

        return CommonResponse.success(HttpStatus.OK);
    }

}

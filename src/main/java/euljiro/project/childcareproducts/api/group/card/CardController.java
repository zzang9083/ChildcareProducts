package euljiro.project.childcareproducts.api.group.card;

import euljiro.project.childcareproducts.api.group.card.dto.CardDto;
import euljiro.project.childcareproducts.api.group.card.dto.CardDtoMapper;
import euljiro.project.childcareproducts.api.group.dto.GroupDto;
import euljiro.project.childcareproducts.api.user.dto.UserDto;
import euljiro.project.childcareproducts.api.user.dto.UserDtoMapper;
import euljiro.project.childcareproducts.application.child.ChildApplicationService;
import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.application.group.GroupCardService;
import euljiro.project.childcareproducts.application.user.UserApplicationService;
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
@RequestMapping("/api/v1/group/{groupToken}")
public class CardController {

    private final GroupCardService groupCardService;

    private final CardDtoMapper cardDtoMapper;



    @PostMapping("/card")
    public CommonResponse registerCard(@PathVariable String groupToken, @RequestBody @Valid CardDto.RegisterCardRequest request) {

        var registerCardRequest = request.toCardCommand(groupToken);

        String cardToken = groupCardService.registerCard(registerCardRequest);

        return CommonResponse.success(new CardDto.RegisterCardResponse(cardToken));
    }

    @GetMapping("/cards")
    public CommonResponse getCards(@PathVariable String groupToken) {

        var cards = groupCardService.getCards(groupToken);

        return CommonResponse.success(new CardDto.GetCardsResponse(cards));
    }

    @PatchMapping("/card/{cardToken}/disable")
    public CommonResponse disableCard(@PathVariable String groupToken, @PathVariable String cardToken) {

        groupCardService.disableCard(cardToken);

        return CommonResponse.success(HttpStatus.OK);
    }

}

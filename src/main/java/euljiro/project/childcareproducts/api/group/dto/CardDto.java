package euljiro.project.childcareproducts.api.group.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupCardCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.group.card.Card;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

public class CardDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterCardRequest {

        @NotEmpty(message = "userKey는 필수값입니다")
        private String userKey;

        @NotEmpty(message = "카드명은 필수값입니다")
        private String cardName;

        @NotEmpty(message = "카드번호는 필수값입니다")
        private String cardNumberSuffix;

        @NotNull(message = "카드사은 필수값입니다")
        @ValidEnum(enumClass = Card.Company.class)
        private Card.Company company;

        public GroupCardCommand.RegisterCardRequest toCardCommand(String groupToken) {
            return GroupCardCommand.RegisterCardRequest.builder()
                    .userKey(userKey)
                    .groupToken(groupToken)
                    .cardName(cardName)
                    .cardNumberSuffix(cardNumberSuffix)
                    .company(company)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterCardResponse {
        private String cardToken;

        public RegisterCardResponse(String cardToken) {
            this.cardToken = cardToken;
        }
    }

    @Getter
    @ToString
    public static class GetCardsResponse {

        private String groupToken;
        private List<CardDto.CardMain> cards;

        public GetCardsResponse(GroupCardInfo.GetCardsResponse cards) {
            this.groupToken = cards.getGroupToken();
            this.cards = mapToItems(cards.getCardList());
        }

        private List<CardDto.CardMain> mapToItems(List<GroupCardInfo.Main> list) {
            if (list == null || list.isEmpty()) {
                return Collections.emptyList();
            }
            return list.stream()
                    .map(this::mapToItem) // 개별 매핑 로직
                    .toList();
        }

        private CardMain mapToItem(GroupCardInfo.Main list) {
            return CardMain.builder()
                    .cardToken(list.getCardToken())
                    .cardName(list.getCardName())
                    .cardNumberSuffix(list.getCardNumberSuffix())
                    .company(list.getCompany())
                    .userId(list.getUserId())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class CardMain {

        private String cardToken;

        private String cardName;

        private String cardNumberSuffix;

        private Card.Company company;

        private String userId;
    }
}

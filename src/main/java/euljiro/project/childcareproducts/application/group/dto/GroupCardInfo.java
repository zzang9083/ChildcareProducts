package euljiro.project.childcareproducts.application.group.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class GroupCardInfo {


    @Getter
    @ToString
    public static class Main {

        private String cardToken;

        private String cardName;

        private String cardNumberSuffix;

        private Card.Company company;

        private String userId;


    }

    @Getter
    @ToString
    public static class RegisterCardResponse {
        private long cardId;
        private String cardToken;


        public RegisterCardResponse(Card card) {
            this.cardId = card.getId();
            this.cardToken = card.getCardToken();
        }
    }

    @Getter
    @ToString
    public static class GetCardsResponse {

        private String groupToken;
        private List<Main> cardList = Lists.newArrayList();

        public GetCardsResponse(Group group) {
            groupToken = group.getGroupToken();
            this.cardList
                    = group.getCardList().stream()
                        .filter(card -> card.getStatus() == Card.Status.ACTIVE)
                        .map(card -> {
                            GroupCardInfo.Main main = new GroupCardInfo.Main();
                            main.cardToken = card.getCardToken();
                            main.cardName = card.getCardName();
                            main.cardNumberSuffix = card.getCardNumberSuffix();
                            main.company = card.getCompany();
                            main.userId = String.valueOf(card.getUserId());
                            return main;
                        })
                    .collect(Collectors.toList());

        }


    }


}

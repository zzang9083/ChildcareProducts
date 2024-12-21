package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class GroupCardCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterCardRequest {

        private String userKey;
        private long userId;

        private String groupToken;

        private String cardNumber;

        private Card.Company company;

        public Card toEntity(Group group) {
            return Card.builder()
                    .group(group)
                    .userId(userId)
                    .cardNumber(cardNumber)
                    .company(company)
                    .build();
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

    }
}

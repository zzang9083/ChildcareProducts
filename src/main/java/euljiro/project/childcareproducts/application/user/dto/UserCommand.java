package euljiro.project.childcareproducts.application.user.dto;

import euljiro.project.childcareproducts.domain.common.Gender;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserCommand {

    private final String userKey;

    private final String nickName;

    private final Gender gender;


    public User toEntity() {
        return User.builder()
                .userKey(userKey)
                .build();

    }

    @Getter
    @Builder
    @ToString
    public static class RegisterUserInfoRequest {
        private String userKey;

        private String nickname;

        private Gender gender;

    }

    @Getter
    @Builder
    @ToString
    public static class RegisterCardRequest {

        private String userKey;

        private String groupToken;

        private String cardNumber;

        private Card.Company company;

    }



}

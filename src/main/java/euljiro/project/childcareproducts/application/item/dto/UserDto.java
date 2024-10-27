package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserDto {




    @Getter
    @Builder
    @ToString
    public static class LoginResponse {

        private final String userKey;

        private final String status;

        private final String token;

        private final String refreshToken;

    }
}

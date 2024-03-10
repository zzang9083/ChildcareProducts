package euljiro.project.childcareproducts.api.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BabyController {
    public static class BabyDto {
    }

    public static class GroupDto {
    }

    public static class UserDto {

        @Getter
        @Setter
        @ToString
        public static class RegisterUserRequest {

        }
    }
}

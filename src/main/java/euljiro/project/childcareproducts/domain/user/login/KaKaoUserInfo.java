package euljiro.project.childcareproducts.domain.user.login;


import euljiro.project.childcareproducts.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KaKaoUserInfo {

    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {

        private Boolean profile_nickname_needs_agreement;
        private Boolean profile_image_needs_agreement;
        private KakaoProfile profile;
    }

    @Getter
    public static class KakaoProfile {

        private String nickname;
        private String thumbnail_image_url;
        private String profile_image_url;
        private Boolean is_default_image;
    }

    public User toEntity() {
        return User.builder()
                .userKey(this.getId().toString())
                .build();
    }
}


package euljiro.project.childcareproducts.infrastructure.user.login;

import euljiro.project.childcareproducts.common.exception.ExternalApiException;
import euljiro.project.childcareproducts.domain.user.login.KaKaoUserInfo;
import euljiro.project.childcareproducts.domain.user.login.KakaoApicaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KaKaoApicallerImpl implements KakaoApicaller {

    private final WebClient webClient;


    @Value("${api.kakao.userInfo_url}")
    private String url ;

    @Override
    public KaKaoUserInfo getUserInfo(String token) {
        Mono<KaKaoUserInfo> response =webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.just(new ExternalApiException()))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.just(new ExternalApiException()))
                .bodyToMono(KaKaoUserInfo.class);
                //.timeout(Duration.ofSeconds(3000));

                return response.block();
    }

}

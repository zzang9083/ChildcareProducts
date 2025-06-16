package euljiro.project.childcareproducts.infrastructure.user.login;

import euljiro.project.childcareproducts.domain.user.login.AppleApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppleApiCallerImpl implements AppleApiCaller {

//    private final WebClient webClient;
//
//    @Value("${api.apple.auth.token-url}")
//    private String url ;
//
//    @Value("${api.apple.aud}")
//    private String clientId ;
//
//    @Value("${api.apple.team-id}")
//    private String teamId ;
//
//    @Value("${api.apple.key.id}")
//    private String keyId ;
//
//    @Value("${api.apple.key.private-key}")
//    private String privateKey;
//    private static final long THIRTY_DAYS_MS = 30L * 24 * 60 * 60 * 1000;
//
//
//    @Override
//    public AppleTokenResponse getAppleToken(String code) {
//        Mono<AppleTokenResponse> response = webClient.post()
//                .uri(uriBuilder -> uriBuilder.path(url)
//                        .queryParam("grant_type", "authorization_code")
//                        .queryParam("client_id", clientId)
//                        .queryParam("client_secret", makeClientSecretToken())
//                        .queryParam("code", code)
//                        .build())
//                .header("HttpHeaders.CONTENT_TYPE", "application/x-www-form-urlencoded;charset=utf-8")
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.just(new ExternalApiException()))
//                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.just(new ExternalApiException()))
//                .bodyToMono(AppleTokenResponse.class);
//        //.timeout(Duration.ofSeconds(3000));
//
//        return response.block();
//    }
//
//    public String makeClientSecretToken() {
//        String token = Jwts.builder()
//                .setSubject(clientId) // sub
//                .setIssuer(teamId) // iss
//                .setIssuedAt(new Date()) // iat
//                .setExpiration((new Date(System.currentTimeMillis() + THIRTY_DAYS_MS))) // exp
//                .setAudience("https://appleid.apple.com") // aud
//                .setHeaderParam("kid", keyId)
//                .signWith(SignatureAlgorithm.ES256, getPrivateKey())
//                .compact();
//        return token;
//    }
//
//    private PrivateKey getPrivateKey() {
//        try {
//            byte[] privateKeyBytes = Decoders.BASE64.decode(privateKey);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("EC");
//            return keyFactory.generatePrivate(keySpec);
//        } catch (Exception e) {
//            throw new RuntimeException("애플 로그인 실패");
//        }
//    }
}

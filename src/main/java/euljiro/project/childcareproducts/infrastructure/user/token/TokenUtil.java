package euljiro.project.childcareproducts.infrastructure.user.token;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private final RedisTemplate redisTemplate;

    // 키-벨류 설정
    public void saveRefreshToken(String userKey, String refreshToken){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(userKey, refreshToken);
        values.set(userKey, refreshToken, Duration.ofHours(3));  // 3시간 뒤 메모리에서 삭제된다.
    }

    // 엔티티토큰/ unique id 저장
    public void storeIdByToken(String token,  long id){
        redisTemplate.opsForValue().set(token, String.valueOf(id));
    }

    public long getIdByToken(String token){
        String idStr = (String)redisTemplate.opsForValue().get(token);
        return idStr != null ? Long.valueOf(idStr) : null;
    }

    // 키값으로 벨류 가져오기
    public String getValues(String key){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    // 키-벨류 삭제
    public void delValues(String userKey) {
        redisTemplate.delete(userKey);
    }







}

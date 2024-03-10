package euljiro.project.childcareproducts.domain.user.sharecode;



import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "sharecode")
public class ShareCode {

    private final static long DEFAULT_EXPIRED_TIME = 86400L;    // 1일

    @Id
    @Indexed
    private String userKey;

    @Indexed
    private String shareCode;

    @TimeToLive
    private long expired;

    public ShareCode(String userKey, String shareCode) {
        this.userKey = userKey;
        this.shareCode = shareCode;
        this.expired   = DEFAULT_EXPIRED_TIME;
    }
}

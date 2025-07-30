package euljiro.project.childcareproducts.domain.user.selectedChild;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "selectedChild")
public class SelectedChild {
    @Id
    @Indexed
    private String userKey;

    @Indexed
    private long selectedChildId;


    public SelectedChild(String userKey, long selectedChildId) {
        this.userKey = userKey;
        this.selectedChildId   = selectedChildId;
    }
}

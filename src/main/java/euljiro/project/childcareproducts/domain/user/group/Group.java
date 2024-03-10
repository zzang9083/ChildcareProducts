package euljiro.project.childcareproducts.domain.user.group;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Entity
@Table(name = "groups")
public class Group {

    private static final String GROUP_PREFIX = "group_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<User> userList = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<Baby> babyList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE("활동"), INACTIVE("비활동");
        private final String description;
    }


    @Builder
    public Group() {
        if (userList == null) throw new InvalidParamException("empty userKey");

        this.groupToken = TokenGenerator.randomCharacterWithPrefix(GROUP_PREFIX);
        this.status = Status.ACTIVE;

    }


}

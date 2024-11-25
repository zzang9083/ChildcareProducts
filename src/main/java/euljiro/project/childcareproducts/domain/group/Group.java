package euljiro.project.childcareproducts.domain.group;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


//@Table(name = "`group`")
@Getter
@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    private static final String GROUP_PREFIX = "grp_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<User> userList = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<Child> childList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE("활동"), INACTIVE("비활동");
        private final String description;
    }


    public Group(List<User> groupingUserList, List<Child> childList) {
        for(User user : groupingUserList) {
            user.matchGroup(this);
        }
        for(Child child : childList) {
            child.setGroup(this);
        }
        this.childList = childList;
        this.groupToken = TokenGenerator.randomCharacterWithPrefix(GROUP_PREFIX);
        this.status = Status.ACTIVE;

    }


}

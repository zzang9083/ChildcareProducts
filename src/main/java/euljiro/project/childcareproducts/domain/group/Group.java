package euljiro.project.childcareproducts.domain.group;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "`groups`" , indexes = @Index(name = "idx_groupToken", columnList = "groupToken", unique = true))
public class Group {

    private static final String GROUP_PREFIX = "grp_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<User> userList = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<Child> childList = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<PuchaseHistory> puchaseHistory = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<Card> cardList = Lists.newArrayList();
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

        //this.selectedChildId = childList.get(0).getId();
        this.groupToken = TokenGenerator.randomCharacterWithPrefix(GROUP_PREFIX);
        this.status = Status.ACTIVE;

    }

    public void removeUser(User user) {
        this.getUserList().remove(user);
    }

    public void setInactiveStatus() {
        this.status = Status.INACTIVE;
    }

    public void joinUser(User user) {
        this.getUserList().add(user);
        user.joinGroup(this);
    }

    public void addChild(Child child) {
        childList.add(child);
        child.setGroup(this);
    }




}

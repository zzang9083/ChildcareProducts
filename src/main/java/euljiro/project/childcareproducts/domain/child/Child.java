package euljiro.project.childcareproducts.domain.child;


import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "child")
public class Child {

    private static final String CHILD_PREFIX = "child_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String childToken;

    private String registeredUserKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    private String childName;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Child(String childName, LocalDate birthday,Status status, String  userKey) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.childName = childName;
        this.birthday = birthday;
        this.registeredUserKey = userKey;
        this.childToken = TokenGenerator.randomCharacterWithPrefix(CHILD_PREFIX);
        this.status = status;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

//    @Builder
//    publid Child() {
//        this.groupToken = TokenGenerator.randomCharacterWithPrefix(GROUP_PREFIX);
//        this.status = Group.Status.ACTIVE;
//    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        BEFORE_BIRTH("출생전"), AFTER_BIRTH("출생후");
        private final String description;
    }


}

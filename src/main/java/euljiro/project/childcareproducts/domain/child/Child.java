package euljiro.project.childcareproducts.domain.child;


import euljiro.project.childcareproducts.common.exception.IllegalStatusException;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
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
@Table(name = "child" , indexes = @Index(name = "idx_childToken", columnList = "childToken", unique = true))
public class Child {

    private static final String CHILD_PREFIX = "chd_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String childToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;


    private String registeredUserKey;

    private String childName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private BirthStatus birthStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Child(String childName, LocalDate birthdate,BirthStatus birthStatus, String  userKey) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.childName = childName;
        this.birthDate = birthdate;
        this.registeredUserKey = userKey;
        this.childToken = TokenGenerator.randomCharacterWithPrefix(CHILD_PREFIX);
        this.birthStatus = birthStatus;
        this.status = Status.ACTIVE;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.getChildList().add(this);
    }

    @Getter
    @RequiredArgsConstructor
    public enum BirthStatus {
        BEFORE_BIRTH("출생전"), AFTER_BIRTH("출생후");
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE("활동"), INACTIVE("바활동");
        private final String description;
    }

    public void disable() {
        if(this.status == Status.INACTIVE)
            throw new IllegalStatusException("이미 삭제된 카드정보입니다.");

        this.status = Status.INACTIVE;
    }


}

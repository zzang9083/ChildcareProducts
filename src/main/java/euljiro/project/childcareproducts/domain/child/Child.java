package euljiro.project.childcareproducts.domain.child;


import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "child")
public class Child {

    private static final String GROUP_PREFIX = "child_";

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

package euljiro.project.childcareproducts.domain.user.group;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "baby")
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String babyToken;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String babyName;

    private LocalDate birthday;



    @Getter
    @RequiredArgsConstructor
    public enum Status {
        BEFORE_BIRTH("출생전"), AFTER_BIRTH("출생후");
        private final String description;
    }
}

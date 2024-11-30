package euljiro.project.childcareproducts.domain.group.card;

import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.group.Group;
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
@Table(name = "Card")
public class Card extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @JoinColumn(name = "group_id")
    private long groupId;

    @Column(length = 100, nullable = false, unique = true)
    private String cardNumber;

    private Company company;
    private String registeredUserKey;

    private Status status;




    @Builder
    public Card(long groupId, String cardNumber
                    , Company company, String registeredUserKey, Status status) {
        //if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.groupId = groupId;
        this.cardNumber = cardNumber;
        this.registeredUserKey = registeredUserKey;
        this.company = company;
        this.status = Status.ACTIVE;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Company {
        KB("KB카드")
        , SHINHAN("신한카드");
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
          ACTIVE("활동중")
        , DELETE("삭제");
        private final String description;
    }


}

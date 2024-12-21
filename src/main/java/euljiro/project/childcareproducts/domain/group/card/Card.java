package euljiro.project.childcareproducts.domain.group.card;

import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
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
@Table(name = "Card" , indexes = @Index(name = "idx_cardToken", columnList = "cardToken", unique = true))
public class Card extends AbstractEntity {

    private static final String CARD_PREFIX = "crd_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardToken;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @JoinColumn(name = "user_id")
    private long userId;

    @Column(length = 100, nullable = false, unique = true)
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private Company company;
    @Enumerated(EnumType.STRING)
    private Status status;




    @Builder
    public Card(Group group, String cardNumber
                    , Company company, long userId) {

        this.cardToken = TokenGenerator.randomCharacterWithPrefix(CARD_PREFIX);

        this.group = group;
        this.group.getCardList().add(this);

        this.cardNumber = cardNumber;
        this.company = company;
        this.status = Status.ACTIVE;

        this.userId = userId;

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

    public void disable() {
        if(this.status == Status.DELETE)
            throw new IllegalStateException();

        this.status = Status.DELETE;
    }


}

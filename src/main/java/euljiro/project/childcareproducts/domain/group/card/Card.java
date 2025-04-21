package euljiro.project.childcareproducts.domain.group.card;

import euljiro.project.childcareproducts.common.exception.IllegalStatusException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.group.Group;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    private String cardName;


    @Column(length = 4)
    private String cardNumberSuffix;

    @Enumerated(EnumType.STRING)
    private Company company;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @JoinColumn(name = "user_id")
    private long userId;





    @Builder
    public Card(Group group, String cardName, String cardNumberSuffix
                    , Company company, long userId) {

        this.cardToken = TokenGenerator.randomCharacterWithPrefix(CARD_PREFIX);

        this.group = group;
        this.group.getCardList().add(this);

        this.cardName = cardName;
        this.cardNumberSuffix = cardNumberSuffix;
        this.company = company;
        this.status = Status.ACTIVE;

        this.userId = userId;

    }

    @Getter
    @RequiredArgsConstructor
    public enum Company {
          SHINHAN("신한카드")
        , SAMSUNG("삼성카드")
        , KB("KB카드")
        , HYMNDAI("현대카드")
        , LOTTE("롯데카드")
        , BC("BC카드")
        , HANA("하나카드")
        , WOORI("우리카드");
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
            throw new IllegalStatusException("이미 삭제된 카드정보입니다.");

        this.status = Status.DELETE;
    }


}

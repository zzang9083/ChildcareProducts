package euljiro.project.childcareproducts.domain.user.card;


import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String groupToken;

    private String userToken;

    private Company company;

    @Getter
    @RequiredArgsConstructor
    public enum Company {
        CASH("KB카드"),
        HANA("하나카드"),
        LOTTE("롯데카드"),
        SHINHAN("신한카드"),
        BC("BC카드"),
        NH("NH농협카드"),
        SAMSUNG("삼성카드"),
        HYUNDAI("현대카드");

        private final String description;
    }



}

package euljiro.project.childcareproducts.domain.group.history;


import euljiro.project.childcareproducts.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "puchaseHistory")
public class PuchaseHistory extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupToken;

    private String itemToken;

    private String productToken;

    private PAYMENT payment;

    private String cardNumber;
    private BigDecimal price;


    @Getter
    @RequiredArgsConstructor
    public enum PAYMENT {
        CASH("현금"),
        OFFLINE("카드"),
        COMPLETE_PURCHASE("나눔");

        private final String description;
    }

    @Builder
    public PuchaseHistory(String groupToken, String itemToken, String productToken, PAYMENT payment, String cardNumber, BigDecimal price) {
        this.groupToken = groupToken;
        this.itemToken = itemToken;
        this.productToken = productToken;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.price = price;
    }

}
package euljiro.project.childcareproducts.domain.group.history;


import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "puchase_history")
public class PuchaseHistory extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private LocalDateTime purchasedDateTime;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    ////////////ITEM/////////////////////////////
    private long itemId;
    private String itemName;

    @Enumerated(EnumType.STRING)
    private Item.Category category;
    /////////////////////////////////////////////

    ////////////PRODUCT//////////////////////////
    private long productId;
    private String productName;

    @Enumerated(EnumType.STRING)
    private Product.PurchaseRoute purchaseRoute;

    @Enumerated(EnumType.STRING)
    private Product.ProductStatus productStatus;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private PAYMENT payment;

    private String cardName;
    private String cardNumberSuffix;

    private Card.Company company;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Getter
    @RequiredArgsConstructor
    public enum PAYMENT {
        CASH("현금"),
        CARD("카드"),
        SHARING("나눔");

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PURCHASED("정상 구매") ,
        CANCELED("사용자/시스템에 의해 취소됨");

        private final String description;

        }

    @Builder
    public PuchaseHistory(Group group, long itemId,String itemName, Item.Category category,
                                        long productId, String productName, Product.PurchaseRoute purchaseRoute
                                            , Product.ProductStatus productStatus, BigDecimal price, PAYMENT payment, String cardName, String cardNumberSuffix, Card.Company company, LocalDateTime purchasedDateTime) {

        this.group = group;
        group.getPuchaseHistory().add(this);
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.productId = productId;
        this.productName = productName;
        this.purchaseRoute = purchaseRoute;
        this.productStatus = productStatus;
        this.price = price;
        this.payment = payment;
        this.cardName = cardName;
        this.cardNumberSuffix = cardNumberSuffix;
        this.company = company;
        this.purchasedDateTime = purchasedDateTime;
        this.status = Status.PURCHASED;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }

}
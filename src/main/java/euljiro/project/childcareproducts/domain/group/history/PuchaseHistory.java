//package euljiro.project.childcareproducts.domain.group.history;
//
//
//import euljiro.project.childcareproducts.domain.AbstractEntity;
//import euljiro.project.childcareproducts.domain.group.Group;
//import euljiro.project.childcareproducts.domain.item.Item;
//import euljiro.project.childcareproducts.domain.product.Product;
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import java.math.BigDecimal;
//
//
//@Getter
//@Entity
//@NoArgsConstructor
//@Table(name = "puchaseHistory")
//public class PuchaseHistory extends AbstractEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "history_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id", nullable = true)
//    private Group group;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id", nullable = true)
//    private Item item;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = true)
//    private Product product;
//
//    private PAYMENT payment;
//    private String cardNumber;
//    private BigDecimal price;
//
//
//    @Getter
//    @RequiredArgsConstructor
//    public enum PAYMENT {
//        CASH("현금"),
//        OFFLINE("카드"),
//        COMPLETE_PURCHASE("나눔");
//
//        private final String description;
//    }
//
//    @Builder
//    public PuchaseHistory(Group group, Item item, Product product, PAYMENT payment, String cardNumber, BigDecimal price) {
//        this.group = group;
//        group.getPuchaseHistory().add(this);
//
//        this.item = item;
//        this.product = product;
//        this.payment = payment;
//        this.cardNumber = cardNumber;
//        this.price = price;
//    }
//
//}
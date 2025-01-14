package euljiro.project.childcareproducts.domain.product;

import euljiro.project.childcareproducts.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "productInquiryHistory")
public class ProductInquiryHistory extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private long groupId;

    private String productToken;

    private String productName;

    private String itemToken;

    private String itemName;

    public ProductInquiryHistory(Product product) {
        this.productToken = product.getProductToken();
        this.productName = product.getProductName();
        this.itemToken = product.getItem().getItemToken();
        this.itemName = product.getItem().getItemName();
        this.groupId = product.getItem().getGroupId();
    }
}

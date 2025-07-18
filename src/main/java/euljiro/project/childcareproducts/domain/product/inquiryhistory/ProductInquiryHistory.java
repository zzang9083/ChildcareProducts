package euljiro.project.childcareproducts.domain.product.inquiryhistory;

import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.product.Product;
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

    private long childId;


    public ProductInquiryHistory(Product product, long childId) {
        this.productToken = product.getProductToken();
        this.productName = product.getProductName();
        this.itemToken = product.getItem().getItemToken();
        this.itemName = product.getItem().getItemName();
        this.groupId = product.getItem().getGroupId();
        this.childId = childId;
    }
}

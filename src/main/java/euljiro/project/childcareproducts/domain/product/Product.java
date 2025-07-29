package euljiro.project.childcareproducts.domain.product;

import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.common.Gender;
import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product", indexes = @Index(name = "idx_productToken", columnList = "productToken", unique = true))
public class Product extends AbstractEntity {

    private static final String PRODUCT_PREFIX = "pdt_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String productToken;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private long registeredUserId;

    @Enumerated(EnumType.STRING)
    private Gender registeredUserGender;

    private String productName;

    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    private PurchaseRoute purchaseRoute;

    @Column(nullable = true)
    private String url;


    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Column(nullable = true)
    private String description;


    @Getter
    @RequiredArgsConstructor
    public enum PurchaseRoute {
        ONLINE ("온라인"),
        OFFLINE ("오프라인"),
        COMPLETE_PURCHASE("나눔");

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ProductStatus {
        NEW("새상품"),
        EXTREMELY_USED("극미중고"),
        USED("중고");

        private final String description;
    }

    @Builder
    public Product(Item item, String productName, BigDecimal price, Product.PurchaseRoute purchaseRoute
            , String url, Product.ProductStatus productStatus, String description, long registeredUserId, Gender registeredUserGender) {
        if (item == null) throw new InvalidParamException("empty itemToken");
        if (StringUtils.isEmpty(purchaseRoute.toString())) throw new InvalidParamException("empty purchaseRoute");

        this.productToken = TokenGenerator.randomCharacterWithPrefix(PRODUCT_PREFIX);

        this.item = item;
        item.getProductList().add(this);

        this.productName = productName;
        this.price = price;
        this.purchaseRoute = purchaseRoute;
        this.url = url;
        this.productStatus = productStatus;
        this.description = description;
        this.registeredUserId = registeredUserId;
        this.registeredUserGender = registeredUserGender;

    }


    public void updateInfo(String productName, PurchaseRoute purchaseRoute, String url
            , ProductStatus productStatus, String description) {
        this.productName = productName;
        this.purchaseRoute = purchaseRoute;
        this.url = url;
        this.productStatus = productStatus;
        this.description = description;
    }


}

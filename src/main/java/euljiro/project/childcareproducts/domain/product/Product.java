package euljiro.project.childcareproducts.domain.product;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product extends AbstractEntity {

    private static final String PRODUCT_PREFIX = "pdt_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productToken;

    private String productName;

    @Enumerated(EnumType.STRING)
    private PurchaseRoute purchaseRoute;

    @Column(nullable = true)
    private String url;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Column(nullable = true)
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
    @Column(name = "item_token") // 물리적 컬럼 이름을 명확히 지정
    private String itemToken;



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
    public Product(String itemToken, String productName, Product.PurchaseRoute purchaseRoute
            , String url, Product.ProductStatus productStatus, String description) {
        if (StringUtils.isEmpty(itemToken)) throw new InvalidParamException("empty itemToken");
        if (StringUtils.isEmpty(purchaseRoute.toString())) throw new InvalidParamException("empty purchaseRoute");

        this.productToken = TokenGenerator.randomCharacterWithPrefix(PRODUCT_PREFIX);
        this.productName = productName;
        this.purchaseRoute = purchaseRoute;
        this.url = url;
        this.productStatus = productStatus;
        this.description = description;
        //this.itemToken = itemToken;

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

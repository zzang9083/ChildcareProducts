package euljiro.project.childcareproducts.domain.item;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.exception.IllegalStatusException;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.common.Gender;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor

@Table(name = "`items`", indexes = {
        @Index(name = "idx_itemToken", columnList = "itemToken", unique = true),
        @Index(name = "idx_groupId", columnList = "groupId")})
public class Item extends AbstractEntity {

    private static final String ITEM_PREFIX = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String itemToken;

    private long groupId;

    private long childId;

    private long registeredUserId;

    @Enumerated(EnumType.STRING)
    private Gender registeredUserGender;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = true)
    private BigDecimal minPrice;

    @Column(nullable = true)
    private BigDecimal maxPrice;

    @Column(nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    //임시상태저장
    private transient Status beforeStatus;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = true)
    private long selectedProductId;

    @Column(nullable = true)
    private LocalDateTime purchasedTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList = Lists.newArrayList();


    @Builder
    public Item(String itemName, long groupId, long childId, long registeredUserId, Gender registeredUserGender, Category category
            , BigDecimal minPrice, BigDecimal maxPrice, String description, ItemStatus itemStatus) {
        if (StringUtils.isEmpty(itemName)) throw new InvalidParamException("empty itemName");
        if (groupId == 0L) throw new InvalidParamException("empty groupId");
        if (StringUtils.isEmpty(category.toString())) throw new InvalidParamException("empty category");

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.itemName = itemName;
        this.groupId = groupId;
        this.childId = childId;
        this.registeredUserId = registeredUserId;
        this.registeredUserGender = registeredUserGender;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.category = category;
        this.itemStatus = itemStatus;
        this.description = description;
        this.status = Status.ON_PURCHASE;

    }

    @Getter
    @RequiredArgsConstructor
    public enum Category {
        APPLIANCES("가전"),
        FUNRITURE( "가구"),
        CLOTHING("의류"),
        STUFF("잡화"),
        BOOKS("도서"),
        ETC("기타");;

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ItemStatus {
        NEW("새상품"),
        EXTREMELY_USED("극미중고"),
        USED("중고");

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        HOLD("구매보류중"),
        ON_PURCHASE("구매중"),
        COMPLETE_PURCHASE("구매완료");

        private final String description;

        @JsonCreator
        public static Status from(String name) {
            return Status.valueOf(name.toUpperCase());
        }
    }

    public void updateInfo(String itemName, Category category, ItemStatus itemStatus
            , BigDecimal minPrice, BigDecimal maxPrice, String description) {
        this.itemName = itemName;
        this.category = category;
        this.itemStatus = itemStatus;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.description = description;
    }

    public void checkValidStatus() {

        if(this.status != Status.ON_PURCHASE) throw new IllegalStateException();
    }

    public void changeStatus(Status newStatus) {
        if(this.status == newStatus) throw new IllegalStatusException("변경전 상태와 변경후 상태가 동일합니다.");

        // 완료 -> 보류 or 구매중
        if(this.status == Status.COMPLETE_PURCHASE) {
            this.selectedProductId = 0L; // 최종선택됐던 상품을 null로
            this.purchasedTime = null;
        }

        this.beforeStatus = this.status; // 변경 전 상태 저장
        this.status = newStatus;
    }

    public boolean isRevertedFromCompletePurchase() {
        return beforeStatus == Status.COMPLETE_PURCHASE && status != Status.COMPLETE_PURCHASE;
    }

    public void confirmPurchase(long selectedProductId) {
        if(this.status == Status.COMPLETE_PURCHASE) throw new IllegalStatusException("이미 구매확정된 품목입니다.");

        this.status = Status.COMPLETE_PURCHASE;
        this.selectedProductId = selectedProductId;
        this.purchasedTime = LocalDateTime.now();

    }


}

package euljiro.project.childcareproducts.domain.item;


import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item {

    private static final String ITEM_PREFIX = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemToken;

    private String itemName;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id")
    private String groupToken;

    private Category category;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String description;

    @Column(nullable = true)
    private String selectedGroupToken;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public Item(String itemName, String groupToken, Category category
            , BigDecimal minPrice, BigDecimal maxPrice, String description, ItemStatus itemStatus) {
        if (StringUtils.isEmpty(itemName)) throw new InvalidParamException("empty itemName");
        if (StringUtils.isEmpty(groupToken)) throw new InvalidParamException("empty groupId");
        if (StringUtils.isEmpty(category.toString())) throw new InvalidParamException("empty category");

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.itemName = itemName;
        this.groupToken = groupToken;
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
        NEW("00", "새상품"),
        EXTREMELY_USED("01", "극미중고"),
        USED("02", "중고");

        private final String code;
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        HOLD("구매보류중"),
        ON_PURCHASE("구매중"),
        COMPLETE_PURCHASE("구매완료");

        private final String description;
    }

    public void updateInfo(String itemName, Category category
            , BigDecimal minPrice, BigDecimal maxPrice, String description) {
        this.itemName = itemName;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.description= description;
    }

    public void checkValidStatus() {
        if(this.status != Status.ON_PURCHASE) throw new IllegalStateException();
    }

    public void changeStatus(Status status) {
        if(this.status == status) throw new  IllegalStateException();

        // 완료 -> 보류 or 구매중
        if(this.status == Status.COMPLETE_PURCHASE) {
            this.selectedGroupToken = null; // 최종선택됐던 상품을 null로
        }
        this.status = status;
    }
}

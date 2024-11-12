package euljiro.project.childcareproducts.domain.item;


import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.domain.group.Group;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private Category category;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();


    @Getter
    @RequiredArgsConstructor
    public enum Category {
        APPLIANCES("00", "가전"),
        FUNRITURE("01","가구"),
        CLOTHING("02","의류"),
        STUFF("03","잡화"),
        BOOKS("04","도서"),
        ETC("05","기타");
        ;

        private final String code;
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ItemStatus {
        NEW("00","새상품"),
        EXTREMELY_USED("01","극미중고"),
        USED("02","중고");

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

}

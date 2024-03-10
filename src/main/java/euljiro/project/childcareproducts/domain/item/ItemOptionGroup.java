package euljiro.project.childcareproducts.domain.item;

import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_option_groups")
public class ItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer ordering;

    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = Lists.newArrayList();


    public ItemOptionGroup addItemOption(ItemOption itemOption) {
        this.itemOptionList.add(itemOption);
        return this;
    }
}

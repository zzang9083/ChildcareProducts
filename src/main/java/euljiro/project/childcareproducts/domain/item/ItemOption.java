package euljiro.project.childcareproducts.domain.item;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_options")
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;

    private Integer ordering;

    private String itemOptionName;


}

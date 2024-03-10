package euljiro.project.childcareproducts.domain.product;

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
@Table(name = "product_option_groups")
public class ProductOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer ordering;

    private String productOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productOptionGroup", cascade = CascadeType.PERSIST)
    private List<ProductOption> productOptionList = Lists.newArrayList();


}

package euljiro.project.childcareproducts.domain.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_options")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_option_group_id")
    private ProductOptionGroup productOptionGroup;

    private Integer ordering;

    private String itemOptionName;


}

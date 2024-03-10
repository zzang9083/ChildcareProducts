package euljiro.project.childcareproducts.domain.product;

import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemToken;

    private String productToken;

    private String productName;

    private String comment;

    private String url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductOptionGroup> productOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @AllArgsConstructor
    public enum Status {
        BEFORE_CHOOSE("채택전"),
        COMPLETE_CHOOSE("채택완료");

        private final String description;
    }
}

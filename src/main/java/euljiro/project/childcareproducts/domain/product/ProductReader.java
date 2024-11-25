package euljiro.project.childcareproducts.domain.product;


import java.util.List;

public interface ProductReader {

    Product findByProductToken(String ProductToken);

}

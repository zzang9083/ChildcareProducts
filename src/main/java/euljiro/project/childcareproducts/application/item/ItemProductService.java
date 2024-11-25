package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemProductService {
    private final ProductService productService;


    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {
        return productService.registerProduct(command);
    }

}

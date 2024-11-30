package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {

    private final ProductRepository ProductRepository;

    @Override
    public Product findByProductId(long productId) {
        return ProductRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품정보입니다."));
    }
}

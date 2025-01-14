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

    private final ProductRepository productRepository;

    @Override
    public Product findByProductId(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품정보입니다."));
    }

    @Override
    public Product findByProductIdWithItem(long ProductId) {
        return productRepository.findByIdWithItem(ProductId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품정보입니다."));
    }
}

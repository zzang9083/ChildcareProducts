package euljiro.project.childcareproducts.infrastructure.product;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    public int getCountBy(Item item) {
        return productRepository.countProductsByItem(item);
    }

    @Override
    @Query(value = "SELECT p FROM Product p WHERE p.item = :itemId")
    public Page<Product> findByItemId(long itemId, Pageable pageable) {
        return productRepository.findByItemId(itemId, pageable);
    }

    @Override
    public Product findByProductIdWithItem(long ProductId) {
        return productRepository.findByIdWithItem(ProductId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품정보입니다."));
    }
}

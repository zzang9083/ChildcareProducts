package euljiro.project.childcareproducts.domain.product;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.common.exception.IllegalStatusException;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductReader productReader;

    private final ProductStore productStore;

    private final ProductInquiryHistoryStore productInquiryHistoryStore;

    @Override
    public ItemProductInfo.RegisterProductResponse registerProduct(Item item, ItemProductCommand.RegisterProductRequest command) {

        // Product Maximum verification
        if(productReader.getCountBy(item) >= 10) {
            throw new IllegalStatusException("물품 최대등록 갯수(10개)를 초과했습니다.");
        }

        // Product Save
        var initProduct = command.toEntity(item);
        Product product = productStore.store(initProduct);

        return new ItemProductInfo.RegisterProductResponse(product);
    }

    @Override
    public ProductInfo.Main getProduct(long productId) {

        // Product Read
        Product product = productReader.findByProductIdWithItem(productId);

        // History Save
        productInquiryHistoryStore.store(new ProductInquiryHistory(product));

        return new ProductInfo.Main(product);
    }

    @Override
    public void updateProduct(ProductCommand.UpdateProductRequest command) {

        Product product = productReader.findByProductId(command.getProductId());

        product.updateInfo(command.getProductName(), command.getPurchaseRoute()
                , command.getUrl(),command.getProductStatus(), command.getDescription());

        productStore.store(product);

    }

    @Override
    public void deleteProduct(long productId) {
        productStore.deleteProductByProductId(productId);
    }

}

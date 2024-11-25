package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ItemProductCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterProductRequest {

        private String itemToken;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;

        public Product toEntity() {
            return Product.builder()
                    .itemToken(itemToken)
                    .productName(productName)
                    .purchaseRoute(purchaseRoute)
                    .url(url)
                    .productStatus(productStatus)
                    .description(description).build();

        }




    }
}

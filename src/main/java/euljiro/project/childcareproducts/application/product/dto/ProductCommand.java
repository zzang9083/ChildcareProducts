package euljiro.project.childcareproducts.application.product.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ProductCommand {
    @Getter
    @Builder
    @ToString
    public static class UpdateProductRequest {

        private long productId;

        private String productToken;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;

        public void setProductId(long productId) {
            this.productId = productId;
        }

    }


}

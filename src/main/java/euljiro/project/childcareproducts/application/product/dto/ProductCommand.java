package euljiro.project.childcareproducts.application.product.dto;

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

        private String productToken;

        private String productName;

        private BigDecimal price;

        private Product.PurchaseRoute purchaseRoute;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;


    }


}

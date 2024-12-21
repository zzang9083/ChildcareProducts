package euljiro.project.childcareproducts.api.product.dto;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.product.dto.ProductCommand;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ProductDto {



    @Getter
    @Builder
    @ToString
    public static class UpdateProductRequest {

        @NotEmpty(message = "제품명은 필수값입니다")
        private String productName;

        @NotEmpty(message = "구매경로는 필수값입니다")
        private Product.PurchaseRoute purchaseRoute;

        @NotEmpty(message = "제품가격은 필수값입니다")
        private BigDecimal price;

        private String url;

        @NotEmpty(message = "제품상태는 필수값입니다")
        private Product.ProductStatus productStatus;

        private String description;

        public ProductCommand.UpdateProductRequest toCommand(String productToken) {
            return ProductCommand.UpdateProductRequest.builder()
                    .productToken(productToken)
                    .productName(this.productName)
                    .price(this.price)
                    .purchaseRoute(this.purchaseRoute)
                    .url(this.url)
                    .productStatus(this.productStatus)
                    .description(this.description).build();
        }

    }


    @Getter
    @Builder
    @ToString
    public static class GetProductResponse {

        private String productToken;

        private String productName;

        private BigDecimal price;

        private Product.PurchaseRoute purchaseRoute;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;
    }

}

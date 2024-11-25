package euljiro.project.childcareproducts.api.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemProductDto {

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

        public ItemProductCommand.RegisterProductRequest toCommand(String itemToken) {
            return ItemProductCommand.RegisterProductRequest.builder()
                    .itemToken(itemToken)
                    .productName(this.productName)
                    .purchaseRoute(this.purchaseRoute)
                    .url(this.url)
                    .productStatus(this.productStatus)
                    .description(this.description).build();
        }
    }
}

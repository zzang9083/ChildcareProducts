package euljiro.project.childcareproducts.api.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ItemProductDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterProductRequest {


        @NotEmpty(message = "제품명은 필수값입니다")
        private String productName;

        private BigDecimal productPrice;


        @NotNull(message = "구매경로는 필수값입니다")
        @ValidEnum(enumClass = Product.PurchaseRoute.class)
        private Product.PurchaseRoute purchaseRoute;

        private String url;

        @NotNull(message = "제품상태는 필수값입니다")
        @ValidEnum(enumClass = Product.ProductStatus.class)
        private Product.ProductStatus productStatus;

        private String description;

        public ItemProductCommand.RegisterProductRequest toCommand(String itemToken) {
            return ItemProductCommand.RegisterProductRequest.builder()
                    .itemToken(itemToken)
                    .productName(this.productName)
                    .price(this.productPrice)
                    .purchaseRoute(this.purchaseRoute)
                    .url(this.url)
                    .productStatus(this.productStatus)
                    .description(this.description).build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmProductRequest {


        @NotEmpty(message = "productToken는 필수값입니다")
        private String productToken;

        @NotNull(message = "결제수단은 필수값입니다")
        @ValidEnum(enumClass = PuchaseHistory.PAYMENT.class)
        private PuchaseHistory.PAYMENT payment;

        private String cardToken;

        public ItemProductCommand.ConfirmProductRequest toCommand(String itemToken) {
            return ItemProductCommand.ConfirmProductRequest.builder()
                    .itemToken(itemToken)
                    .productToken(this.productToken)
                    .payment(this.payment)
                    .cardToken(this.cardToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class GetItemProductResponse {

        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        private Item.Status status;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime creationTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updateTime;


        private List<ProductSummary> productList;


        @Getter
        @Builder
        @ToString
        public static class ProductSummary {
            private final String productToken;

            private final String productName;

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            private final LocalDateTime creationTime;
        }


    }
}

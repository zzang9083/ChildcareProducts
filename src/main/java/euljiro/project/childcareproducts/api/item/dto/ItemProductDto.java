package euljiro.project.childcareproducts.api.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
//import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemProductDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterProductRequest {

        @NotEmpty(message = "itemToken는 필수값입니다")
        private String itemToken;

        @NotEmpty(message = "제품명은 필수값입니다")
        private String productName;

        @NotEmpty(message = "구매경로는 필수값입니다")
        private Product.PurchaseRoute purchaseRoute;

        private String url;

        @NotEmpty(message = "제품상태는 필수값입니다")
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

    @Getter
    @Builder
    @ToString
    public static class ConfirmProductRequest {


        @NotEmpty(message = "itemToken는 필수값입니다")
        private String itemToken;

        @NotEmpty(message = "productToken는 필수값입니다")
        private String productToken;

        @NotEmpty(message = "결제수단은 필수값입니다")
        private PuchaseHistory.PAYMENT payment;

        private String cardNumber;

        public ItemProductCommand.ConfirmProductRequest toCommand(String itemToken) {
            return ItemProductCommand.ConfirmProductRequest.builder()
                    .itemToken(itemToken)
                    .productToken(this.productToken)
                    .payment(this.payment)
                    .cardNumber(this.cardNumber)
                    .build();
        }
    }
}

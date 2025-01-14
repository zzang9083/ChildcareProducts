package euljiro.project.childcareproducts.api.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
//import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
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

//        @NotEmpty(message = "itemToken는 필수값입니다")
        private String itemToken;


        @NotEmpty(message = "제품명은 필수값입니다")
        private String productName;

        private BigDecimal productPrice;


        //        @NotEmpty(message = "구매경로는 필수값입니다")
//        @ValidEnum(enumClass = Product.PurchaseRoute.class)
        private Product.PurchaseRoute purchaseRoute;

        private String url;

//        @NotEmpty(message = "제품상태는 필수값입니다")
//        @ValidEnum(enumClass = Product.ProductStatus.class)
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


        //@NotEmpty(message = "itemToken는 필수값입니다")
        private String itemToken;

        //@NotEmpty(message = "productToken는 필수값입니다")
        private String productToken;

        //@NotEmpty(message = "결제수단은 필수값입니다")
        //@ValidEnum(enumClass = PuchaseHistory.PAYMENT.class)
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

package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemProductCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterProductRequest {


        private String itemToken;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private BigDecimal price;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;

        public Product toEntity(Item item) {
            return Product.builder()
                    .item(item)
                    .productName(productName)
                    .price(price)
                    .purchaseRoute(purchaseRoute)
                    .url(url)
                    .productStatus(productStatus)
                    .description(description).build();

        }

    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmProductRequest {
        private String itemToken;

        private String groupToken;

        private String productToken;

        private PuchaseHistory.PAYMENT payment;

        private String cardNumber;


        private long groupId;

        public void setGroupToken(String groupToken) {
            this.groupToken = groupToken;
        }


    }
}

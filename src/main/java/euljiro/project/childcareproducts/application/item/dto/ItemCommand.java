package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
//import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemCommand {

    @Getter
    @Builder
    @ToString
    public static class UpdateItemRequest {

        private long itemId;

        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        public void setItemId(long itemId) {
            this.itemId = itemId;
        }

    }

    @Getter
    @Builder
    @ToString
    public static class ChangeStatusRequest {

        private long itemId;

        private String itemToken;

        private Item.Status status;

        public void setItemId(long itemId) {
            this.itemId = itemId;
        }

    }

    @Getter
    @ToString
    public static class ConfirmPurchaseRequest {

        private long groupId;

        ////////////ITEM/////////////////////////////
        private String itemName;

        private Item.Category category;
        /////////////////////////////////////////////

        ////////////PRODUCT//////////////////////////
        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private Product.ProductStatus productStatus;

        private BigDecimal price;

        private PuchaseHistory.PAYMENT payment;

        private String cardNumber;

        public ConfirmPurchaseRequest(ItemProductCommand.ConfirmProductRequest command
                                        , GroupItemInfo.SpecificItemAndProductResponse info) {

            this.payment = command.getPayment();
            this.cardNumber = command.getCardNumber();

            this.groupId = info.getGroupId();
            this.itemName = info.getItemName();
            this.category = info.getCategory();
            this.productName = info.getProductName();
            this.purchaseRoute = info.getPurchaseRoute();
            this.productStatus = info.getProductStatus();
            this.price = info.getPrice();

        }

        public PuchaseHistory toHistoryEntity(Group group) {
            return PuchaseHistory.builder()
                    .group(group)
                    .itemName(itemName)
                    .category(category)
                    .productName(productName)
                    .purchaseRoute(purchaseRoute)
                    .productStatus(productStatus)
                    .price(price)
                    .payment(payment)
                    .cardNumber(cardNumber).build();
        }

    }



}

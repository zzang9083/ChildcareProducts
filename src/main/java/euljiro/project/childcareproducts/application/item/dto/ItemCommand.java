package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemCommand {

    @Getter
    @Builder
    @ToString
    public static class UpdateItemRequest {


        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;


    }

    @Getter
    @Builder
    @ToString
    public static class ChangeStatusRequest {


        private String itemToken;

        private Item.Status status;


    }

//    @Getter
//    @ToString
//    public static class ConfirmPurchaseRequest {
//
//        private long groupId;
//
//        ////////////ITEM/////////////////////////////
//        private String itemName;
//
//        private Item.Category category;
//        /////////////////////////////////////////////
//
//        ////////////PRODUCT//////////////////////////
//        private String productName;
//
//        private Product.PurchaseRoute purchaseRoute;
//
//        private Product.ProductStatus productStatus;
//
//        private BigDecimal price;
//
//        private PuchaseHistory.PAYMENT payment;
//
//        private String cardNumber;
//
//        public ConfirmPurchaseRequest(ItemProductCommand.ConfirmProductRequest command) {
//
//            this.payment = command.getPayment();
//            this.cardNumber = command.getCardNumber();
//
//            this.groupId = info.getGroupId();
//            this.itemName = info.getItemName();
//            this.category = info.getCategory();
//            this.productName = info.getProductName();
//            this.purchaseRoute = info.getPurchaseRoute();
//            this.productStatus = info.getProductStatus();
//            this.price = info.getPrice();
//
//        }
//
//        public PuchaseHistory toHistoryEntity(Group group) {
//            return PuchaseHistory.builder()
//                    .group(group)
//                    .itemName(itemName)
//                    .category(category)
//                    .productName(productName)
//                    .purchaseRoute(purchaseRoute)
//                    .productStatus(productStatus)
//                    .price(price)
//                    .payment(payment)
//                    .cardNumber(cardNumber)
//                    .purchasedDateTime(LocalDateTime.now())
//                    .build();
//        }
//
//    }



}

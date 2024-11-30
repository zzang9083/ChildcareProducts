package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
//import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
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

        private String productToken;

        public void setItemId(long itemId) {
            this.itemId = itemId;
        }

    }

    @Getter
    @ToString
    public static class ConfirmPurchaseRequest {

        private long itemId;

        private String itemToken;

        //private PuchaseHistory.PAYMENT payment;

        private String cardNumber;

        public void setItemId(long itemId) {
            this.itemId = itemId;
        }

//        public ConfirmPurchaseRequest(String itemToken, PuchaseHistory.PAYMENT payment, String cardNumber) {
//            this.itemToken = itemToken;
//            this.payment = payment;
//            this.cardNumber = cardNumber;
//        }

    }



}

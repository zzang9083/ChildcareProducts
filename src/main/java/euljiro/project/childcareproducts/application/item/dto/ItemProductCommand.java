package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.common.Gender;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
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
import java.time.LocalDateTime;

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

        private String userKey;

        public Product toEntity(Item item, User user) {
            return Product.builder()
                    .item(item)
                    .productName(productName)
                    .price(price)
                    .purchaseRoute(purchaseRoute)
                    .url(url)
                    .productStatus(productStatus)
                    .description(description)
                    .registeredUserId(user.getId())
                    .registeredUserGender(user.getGender()).build();

        }

    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmProductRequest {

        ////////////INPUT

        private String itemToken;

        private String productToken;

        private PuchaseHistory.PAYMENT payment;

        private String cardToken;

        private BigDecimal price;

        /////////////group///////////////////////////////

        private Group group;

        /////////////item///////////////////////////////

        private long itemId;

        private String itemName;

        private Item.Category category;

        /////////////prduct///////////////////////////////

        private long productId;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private Product.ProductStatus productStatus;


        /////////////card///

        private String cardName;

        private String cardNumberSuffix;

        private Card.Company company;
        public void setPurchaseInfo(Item item, ProductInfo.Main product, Card card, Group group) {

            this.itemId   = item.getId();
            this.itemName = item.getItemName();
            this.category = item.getCategory();

            this.group = group;

            this.productId   = product.getId();
            this.productName = product.getProductName();
            this.purchaseRoute = product.getPurchaseRoute();
            this.productStatus = product.getProductStatus();

            if(card != null) {
                this.cardName = card.getCardName();
                this.cardNumberSuffix = card.getCardNumberSuffix();
                this.company = card.getCompany();
            }
        }

        public PuchaseHistory toHistoryEntity() {
            return PuchaseHistory.builder()
                    .group(group)
                    .itemId(itemId)
                    .itemName(itemName)
                    .category(category)
                    .productId(productId)
                    .productName(productName)
                    .purchaseRoute(purchaseRoute)
                    .productStatus(productStatus)
                    .price(price)
                    .payment(payment)
                    .cardName(cardName)
                    .cardNumberSuffix(cardNumberSuffix)
                    .company(company)
                    .purchasedDateTime(LocalDateTime.now())
                    .build();
        }


    }
}

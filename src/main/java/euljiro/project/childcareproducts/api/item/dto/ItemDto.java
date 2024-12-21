package euljiro.project.childcareproducts.api.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class ItemDto {



    @Getter
    @Builder
    @ToString
    public static class UpdateItemRequest {

        @NotEmpty(message = "itemName는 필수값입니다")
        private String itemName;

        @NotEmpty(message = "category는 필수값입니다")
        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        @NotEmpty(message = "itemStatus는 필수값입니다")
        private Item.ItemStatus itemStatus;

        private String description;

        public ItemCommand.UpdateItemRequest toCommand(String itemToken) {
            return ItemCommand.UpdateItemRequest.builder()
                    .itemToken(itemToken)
                    .itemName(this.itemName)
                    .category(this.category)
                    .minPrice(this.minPrice)
                    .maxPrice(this.maxPrice)
                    .itemStatus(this.itemStatus)
                    .description(this.description).build();
        }

    }

    @Getter
    @Builder
    @ToString
    public static class ChangeStatusRequest {

        @NotEmpty(message = "status는 필수값입니다")
        private Item.Status status;

        public ItemCommand.ChangeStatusRequest toCommand(String itemToken) {
            return ItemCommand.ChangeStatusRequest.builder()
                    .itemToken(itemToken)
                    .status(this.status)
                    .build();
        }

    }


    @Getter
    @Builder
    @ToString
    public static class RegisterItemResponse {

        private String groupToken;

    }

    @Getter
    @Builder
    @ToString
    public static class GetItemResponse {

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

    }

    @Getter
    @Builder
    @ToString
    public static class GetItemDetailResponse {

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


        private List<ProductInfo> productList;

        @Getter
        @Builder
        @ToString
        public static class ProductInfo {
            private final String productToken;
            private final String productName;
            private final Product.PurchaseRoute purchaseRoute;
            private final String url;
            private final Product.ProductStatus productStatus;
            private final String description;
        }


    }

}

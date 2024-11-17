package euljiro.project.childcareproducts.api.item.dto;

import euljiro.project.childcareproducts.application.complex.dto.GroupCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.*;

public class ItemDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterItemRequest {

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        public ItemCommand.RegisterItemRequest toCommand() {
            return ItemCommand.RegisterItemRequest.builder()
                    .groupToken(this.groupToken)
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
    public static class UpdateItemRequest {

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

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

        private Item.Status status;

        private String productToken;

        public ItemCommand.ChangeStatusRequest toCommand(String itemToken) {
            return ItemCommand.ChangeStatusRequest.builder()
                    .itemToken(itemToken)
                    .status(this.status)
                    .productToken(this.productToken)
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

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        private Item.Status status;
    }

}

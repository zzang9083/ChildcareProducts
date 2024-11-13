package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.common.util.TokenGenerator;
import euljiro.project.childcareproducts.domain.group.Group;
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
    public static class RegisterItemRequest {

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        public Item toEntity() {
            return Item.builder()
                    .itemName(itemName)
                    .groupToken(groupToken)
                    .minPrice(minPrice)
                    .maxPrice(maxPrice)
                    .category(category)
                    .description(description)
                    .itemStatus(itemStatus).build();

        }




    }

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

        private String productToken;



    }
}
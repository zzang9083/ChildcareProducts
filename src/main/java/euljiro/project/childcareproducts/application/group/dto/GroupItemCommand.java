package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class GroupItemCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterItemRequest {

        private long groupId;

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
                    .groupId(groupId)
                    .minPrice(minPrice)
                    .maxPrice(maxPrice)
                    .category(category)
                    .description(description)
                    .itemStatus(itemStatus).build();

        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

    }
}

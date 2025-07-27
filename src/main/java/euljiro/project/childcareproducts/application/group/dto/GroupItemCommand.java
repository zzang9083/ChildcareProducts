package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.common.Gender;
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

        private String groupToken;

        private String childToken;

        private String userKey;
        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        public Item toEntity(long groupId, long childId, long registeredUserId, Gender registeredUserGender) {
            return Item.builder()
                    .itemName(itemName)
                    .groupId(groupId)
                    .childId(childId)
                    .registeredUserId(registeredUserId)
                    .registeredUserGender(registeredUserGender)
                    .minPrice(minPrice)
                    .maxPrice(maxPrice)
                    .category(category)
                    .description(description)
                    .itemStatus(itemStatus).build();

        }

    }

    @Getter
    @ToString
    public static class GetItemsRequest {

        private String groupToken;

        private String childToken;

        private Item.Status status;

        public GetItemsRequest(String groupToken, String childToken, Item.Status status) {
            this.groupToken = groupToken;
            this.childToken = childToken;
            this.status = status;

        }
    }
}

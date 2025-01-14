package euljiro.project.childcareproducts.api.group.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.*;
import java.math.BigDecimal;

public class GroupDto {

    @Getter
    @Builder
    @ToString
    public static class RegisterItemRequest {

        @NotEmpty(message = "품목명은 필수값입니다")
        private String itemName;

        //@NotEmpty(message = "카테고리는 필수값입니다")
        //@ValidEnum(enumClass = euljiro.project.childcareproducts.domain.item.Item.Category.class)
        private euljiro.project.childcareproducts.domain.item.Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;
        //@NotEmpty(message = "품목상태는 필수값입니다")
        //@ValidEnum(enumClass = euljiro.project.childcareproducts.domain.item.Item.ItemStatus.class)
        private euljiro.project.childcareproducts.domain.item.Item.ItemStatus itemStatus;

        private String description;

        public GroupItemCommand.RegisterItemRequest toCommand(String groupToken) {
            return GroupItemCommand.RegisterItemRequest.builder()
                    .groupToken(groupToken)
                    .itemName(this.itemName)
                    .category(this.category)
                    .minPrice(this.minPrice)
                    .maxPrice(this.maxPrice)
                    .itemStatus(this.itemStatus)
                    .description(this.description).build();
        }
    }
    @Getter
    @ToString
    public static class RegisterItemResponse {
        private String itemToken;

        public RegisterItemResponse(String itemToken) {
            this.itemToken = itemToken;
        }
    }

    @Getter
    @ToString
    public static class GetItemsResponse {

        private List<Item> items;

        public GetItemsResponse(GroupItemInfo.MainList mainList) {
            this.items = mapToItems(mainList.getMainList());
        }

        private List<Item> mapToItems(List<GroupItemInfo.Main> mainList) {
            if (mainList == null || mainList.isEmpty()) {
                return Collections.emptyList();
            }
            return mainList.stream()
                    .map(this::mapToItem) // 개별 매핑 로직
                    .toList();
        }

        private Item mapToItem(GroupItemInfo.Main mainItem) {
            return Item.builder()
                    .itemToken(mainItem.getItemToken())
                    .itemName(mainItem.getItemName())
                    .category(mainItem.getCategory())
                    .itemStatus(mainItem.getItemStatus())
                    .status(mainItem.getStatus())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class Item {

        private String itemToken;

        private String itemName;

        private euljiro.project.childcareproducts.domain.item.Item.Category category;

        private euljiro.project.childcareproducts.domain.item.Item.ItemStatus itemStatus;

        private euljiro.project.childcareproducts.domain.item.Item.Status status;
    }
}

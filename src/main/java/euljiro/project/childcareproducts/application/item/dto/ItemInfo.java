package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemInfo {

    @Getter
    @Builder
    @ToString
    public static class Main {

        private String itemToken;

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private String description;

        private String selectedGroupToken;
        private Item.ItemStatus itemStatus;

        private Item.Status status;

        public Main(Item item) {
            this.itemToken = item.getItemToken();
            this.groupToken = item.getGroupToken();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            this.minPrice = item.getMinPrice();
            this.maxPrice = item.getMaxPrice();
            this.description = item.getDescription();
            this.selectedGroupToken = item.getSelectedGroupToken();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
        }
    }


}

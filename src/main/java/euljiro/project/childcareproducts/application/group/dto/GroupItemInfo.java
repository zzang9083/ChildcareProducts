package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

public class GroupItemInfo {

    @Getter
    @ToString
    public static class MainList {
        private List<Main> mainList;

        public MainList(List<Item> items) {
            this.mainList = items.stream()
                    .map(Main::new) // Item을 Main으로 변환
                    .toList();      // 변환된 리스트를 List<Main>으로 변경
        }
    }
    @Getter
    @ToString
    public static class Main {

        private String itemToken;

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private String description;

        private String selectedProductToken;
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
            this.selectedProductToken = item.getSelectedProductToken();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
        }
    }
}

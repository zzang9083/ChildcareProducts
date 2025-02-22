package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class ItemInfo {

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

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private String description;

        private Item.ItemStatus itemStatus;

        private Item.Status status;

        private LocalDateTime creationTime;

        private LocalDateTime updateTime;


        public Main(Item item) {
            this.itemToken = item.getItemToken();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            this.minPrice = item.getMinPrice();
            this.maxPrice = item.getMaxPrice();
            this.description = item.getDescription();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
            this.creationTime = item.getCreationTime();
            this.updateTime = item.getUpdateTime();
        }
    }




}

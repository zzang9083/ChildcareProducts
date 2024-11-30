package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Getter;
import lombok.ToString;

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

        private String selectedProductToken;
        private Item.ItemStatus itemStatus;

        private Item.Status status;

        private LocalDateTime creationTime;

        private LocalDateTime updateTime;

        private LocalDateTime purchasedTime;

        public Main(Item item) {
            this.itemToken = item.getItemToken();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            this.minPrice = item.getMinPrice();
            this.maxPrice = item.getMaxPrice();
            this.description = item.getDescription();
            this.selectedProductToken = item.getSelectedProductToken();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
            this.creationTime = item.getCreationTime();
            this.updateTime = item.getUpdateTime();
            this.purchasedTime = item.getPurchasedTime();
        }
    }

    @Getter
    @ToString
    public static class MainDetail {

        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;
        private Item.Status status;
        private String selectedProductToken;


        private LocalDateTime creationTime;

        private LocalDateTime updateTime;

        private LocalDateTime purchasedTime;

        private List<ProductInfo.Main> productList;



        public MainDetail(Item item) {
            this.itemToken = item.getItemToken();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            this.minPrice = item.getMinPrice();
            this.maxPrice = item.getMaxPrice();
            this.description = item.getDescription();
            this.selectedProductToken = item.getSelectedProductToken();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
            this.creationTime = item.getCreationTime();
            this.updateTime = item.getUpdateTime();
            this.purchasedTime = item.getPurchasedTime();

            if(item.getProductList() != null && !item.getProductList().isEmpty()) {
                for(Product product : item.getProductList()) {
                    productList.add(new ProductInfo.Main(product));
                }
            }
        }
    }


}

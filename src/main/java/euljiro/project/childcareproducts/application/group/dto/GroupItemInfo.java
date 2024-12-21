package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
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

        private long groupId;

        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private String description;

        private long selectedProductId;
        private Item.ItemStatus itemStatus;

        private Item.Status status;

        public Main(Item item) {
            this.groupId = item.getGroupId();
            this.itemToken = item.getItemToken();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            this.minPrice = item.getMinPrice();
            this.maxPrice = item.getMaxPrice();
            this.description = item.getDescription();
            this.selectedProductId = item.getSelectedProductId();
            this.itemStatus = item.getItemStatus();
            this.status = item.getStatus();
        }
    }

    @Getter
    @ToString
    public static class RegisterItemResponse {
        private long itemId;
        private String itemToken;


        public RegisterItemResponse(Item item) {
            this.itemId = item.getId();
            this.itemToken = item.getItemToken();
        }
    }

    @Getter
    @ToString
    public static class SpecificItemAndProductResponse {

        private long groupId;

        ////////////ITEM/////////////////////////////
        private String itemName;

        private Item.Category category;
        /////////////////////////////////////////////

        ////////////PRODUCT//////////////////////////
        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private Product.ProductStatus productStatus;

        private BigDecimal price;

        public SpecificItemAndProductResponse(Item item) {

            Product findProduct = item.getProductList().get(0);

            this.groupId = item.getGroupId();
            this.itemName = item.getItemName();
            this.category = item.getCategory();
            if(findProduct != null) {
                this.productName = findProduct.getProductName();
                this.purchaseRoute = findProduct.getPurchaseRoute();
                this.price = findProduct.getPrice();
                this.productStatus = findProduct.getProductStatus();
            }

        }

    }
}

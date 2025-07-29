package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.common.Gender;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ItemProductInfo {

    @Getter
    @ToString
    public static class RegisterProductResponse {
        private long productId;
        private String productToken;

        public RegisterProductResponse(Product product) {
            this.productId = product.getId();
            this.productToken = product.getProductToken();
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

        private Item.ItemStatus itemStatus;

        private String description;
        private Item.Status status;

        private long registeredUserId;

        private Gender registeredUserGender;

        private LocalDateTime creationTime;

        private LocalDateTime updateTime;

        private List<ProductSummary> productList;




        public Main(Item item) {

            // item
            if (item != null) {
                this.itemToken = item.getItemToken();
                this.itemName = item.getItemName();
                this.category = item.getCategory();
                this.minPrice = item.getMinPrice();
                this.maxPrice = item.getMaxPrice();
                this.description = item.getDescription();
                this.itemStatus = item.getItemStatus();
                this.status = item.getStatus();
                this.registeredUserId = item.getRegisteredUserId();
                this.registeredUserGender = item.getRegisteredUserGender();
                this.creationTime = item.getCreationTime();
                this.updateTime = item.getUpdateTime();
            }

            // products
            if(item.getProductList() != null && item.getProductList().size() > 0 ) {
                // 리스트 변환
                this.productList = item.getProductList().stream()
                        .map(ItemProductInfo.ProductSummary::new) // Product를 Main으로 변환
                        .toList();
            }


        }
    }

    @Getter
    @ToString
    public static class ProductSummary {

        private String productToken;

        private String productName;

        private LocalDateTime creationTime;

        public ProductSummary(Product product) {
            this.productToken = product.getProductToken();
            this.productName = product.getProductName();
            this.creationTime = product.getCreationTime();

        }
    }
}

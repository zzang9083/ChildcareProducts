package euljiro.project.childcareproducts.application.product.dto;

import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ProductInfo {

    @Getter
    @ToString
    public static class Main {

        private long id;

        private String productToken;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private BigDecimal price;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;




        public Main(Product product) {
            this.id = product.getId();
            this.productToken = product.getProductToken();
            this.productName = product.getProductName();
            this.purchaseRoute = product.getPurchaseRoute();
            this.price = product.getPrice();
            this.url = product.getUrl();
            this.productStatus = product.getProductStatus();
            this.description = product.getDescription();

        }
    }
}

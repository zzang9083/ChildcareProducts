package euljiro.project.childcareproducts.application.product.dto;

import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Getter;
import lombok.ToString;

public class ProductInfo {

    @Getter
    @ToString
    public static class Main {

        private String productToken;

        private String productName;

        private Product.PurchaseRoute purchaseRoute;

        private String url;

        private Product.ProductStatus productStatus;

        private String description;

        private String itemToken;



        public Main(Product product) {
            this.productToken = product.getProductToken();
            this.productName = product.getProductName();
            this.purchaseRoute = product.getPurchaseRoute();
            this.url = product.getUrl();
            this.productStatus = product.getProductStatus();
            this.description = product.getDescription();
            this.itemToken = product.getItemToken();

        }
    }
}

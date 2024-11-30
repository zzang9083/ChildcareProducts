package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
}

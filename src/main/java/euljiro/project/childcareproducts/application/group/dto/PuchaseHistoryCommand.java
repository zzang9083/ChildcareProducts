package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class PuchaseHistoryCommand {

    @Getter
    @ToString
    public static class GetPuchasesRequest {

        private String groupToken;
        private Item.Category category;
        private Product.PurchaseRoute purchaseRoute;
        private Product.ProductStatus productStatus;
        private LocalDate startDate;
        private LocalDate endDate;


        public GetPuchasesRequest(String groupToken, Item.Category category, Product.PurchaseRoute purchaseRoute
                                    , Product.ProductStatus productStatus, LocalDate startDate, LocalDate endDate) {
            this.groupToken = groupToken;
            this.category = category;
            this.purchaseRoute = purchaseRoute;
            this.productStatus = productStatus;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
}

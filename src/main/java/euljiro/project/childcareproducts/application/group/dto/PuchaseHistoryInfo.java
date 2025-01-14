package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Objects;

public class PuchaseHistoryInfo {

    @Getter
    @ToString
    public static class GetPuchasesResponse {

        List<Main> puchaseList;

        int count;

        BigDecimal totalAmount;


        public GetPuchasesResponse(List<PuchaseHistory> filteredPurchaseHistories) {
            this.puchaseList = filteredPurchaseHistories.stream()
                    .map(Main::new)
                    .toList();

            // Stream을 한 번 더 사용해 count와 totalAmount 계산
            this.count = filteredPurchaseHistories.size();
            this.totalAmount = filteredPurchaseHistories.stream()
                    .map(PuchaseHistory::getPrice)
                    .filter(Objects::nonNull) // null 가격 방지
                    .reduce(BigDecimal.ZERO, BigDecimal::add); // 가격 합산


        }


    }

    @Getter
    @ToString
    public static class Main {

        private Long historyId;

        private LocalDateTime purchasedDateTime;

        private String itemName;

        private Item.Category category;

        private String productName;
        private Product.PurchaseRoute purchaseRoute;

        private Product.ProductStatus productStatus;

        private BigDecimal price;

        private PuchaseHistory.PAYMENT payment;

        private String cardNumber;

        public Main(PuchaseHistory puchaseHistory) {
            this.historyId = puchaseHistory.getId();
            this.purchasedDateTime = puchaseHistory.getPurchasedDateTime();
            this.itemName = puchaseHistory.getItemName();
            this.category = puchaseHistory.getCategory();
            this.productName = puchaseHistory.getProductName();
            this.purchaseRoute = puchaseHistory.getPurchaseRoute();
            this.productStatus = puchaseHistory.getProductStatus();
            this.price = puchaseHistory.getPrice();
            this.payment = puchaseHistory.getPayment();
            this.cardNumber = puchaseHistory.getCardNumber();
        }
    }

}

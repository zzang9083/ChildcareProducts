package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Objects;

public class PuchaseHistoryInfo {

    @Getter
    @ToString
    public static class GetPuchasesResponse {


        private BigDecimal totalAmount;
        private int currentPage;

        private int totalPages;

        private long totalItemCount;
        private List<Main> puchaseList;




        public GetPuchasesResponse(BigDecimal totalAmount, Page<PuchaseHistory> filteredPurchaseHistories) {

            this.totalAmount = totalAmount;

            this.currentPage = filteredPurchaseHistories.getNumber();
            this.totalPages = filteredPurchaseHistories.getTotalPages();
            this.totalItemCount = filteredPurchaseHistories.getTotalElements();
            this.puchaseList = filteredPurchaseHistories.stream()
                    .map(Main::new)
                    .toList();



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

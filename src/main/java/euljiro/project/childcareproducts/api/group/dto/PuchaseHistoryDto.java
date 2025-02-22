package euljiro.project.childcareproducts.api.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class PuchaseHistoryDto {

    @Getter
    @ToString
    public static class GetPuchasesResponse {

        List<PuchaseHistory> puchaseList;

        long count;

        BigDecimal totalAmount;

        public GetPuchasesResponse(PuchaseHistoryInfo.GetPuchasesResponse filteredPurchaseHistories) {

            this.puchaseList = mapToHistoryList(filteredPurchaseHistories.getPuchaseList());

            this.count = filteredPurchaseHistories.getTotalItemCount();

            this.totalAmount = filteredPurchaseHistories.getTotalAmount();

        }
        private List<PuchaseHistoryDto.PuchaseHistory> mapToHistoryList(List<PuchaseHistoryInfo.Main> puchaseList) {
            if (puchaseList == null || puchaseList.isEmpty()) {
                return Collections.emptyList();
            }


            return puchaseList.stream()
                    .map(this::mapToHistory) // 개별 매핑 로직
                    .toList();
        }

        private PuchaseHistory mapToHistory(PuchaseHistoryInfo.Main puchaseList) {
            return PuchaseHistory.builder()
                    .historyId(puchaseList.getHistoryId())
                    .purchasedDateTime(puchaseList.getPurchasedDateTime())
                    .itemName(puchaseList.getItemName())
                    .category(puchaseList.getCategory())
                    .productName(puchaseList.getProductName())
                    .purchaseRoute(puchaseList.getPurchaseRoute())
                    .productStatus(puchaseList.getProductStatus())
                    .price(puchaseList.getPrice())
                    .payment(puchaseList.getPayment())
                    .cardNumber(puchaseList.getCardNumber())
                    .build();
        }


    }

    @Getter
    @Builder
    @ToString
    public static class PuchaseHistory {

        private Long historyId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime purchasedDateTime;

        private String itemName;

        private Item.Category category;

        private String productName;
        private Product.PurchaseRoute purchaseRoute;

        private Product.ProductStatus productStatus;

        private BigDecimal price;

        private euljiro.project.childcareproducts.domain.group.history.PuchaseHistory.PAYMENT payment;

        private String cardNumber;

//        public PuchaseHistory(PuchaseHistory puchaseHistory) {
//            this.historyId = puchaseHistory.getHistoryId();
//            this.purchasedDate = puchaseHistory.getPurchasedDate();
//            this.itemName = puchaseHistory.getItemName();
//            this.category = puchaseHistory.getCategory();
//            this.productName = puchaseHistory.getProductName();
//            this.purchaseRoute = puchaseHistory.getPurchaseRoute();
//            this.productStatus = puchaseHistory.getProductStatus();
//            this.price = puchaseHistory.getPrice();
//            this.payment = puchaseHistory.getPayment();
//            this.cardNumber = puchaseHistory.getCardNumber();
//        }
    }
}

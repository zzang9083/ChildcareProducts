package euljiro.project.childcareproducts.api.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.group.dto.PuchaseHistoryInfo;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import lombok.AllArgsConstructor;
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
    public static class GetPurchaseHistoriesResponse {


        private int currentPage;

        private int totalPages;

        private long totalItemCount;
        List<PuchaseHistorySummary> puchaseList;


        public GetPurchaseHistoriesResponse(PuchaseHistoryInfo.GetPurchaseHistoriesResponse purchaseHistories) {

            this.currentPage = purchaseHistories.getCurrentPage();
            this.totalPages = purchaseHistories.getTotalPages();
            this.totalItemCount = purchaseHistories.getTotalItemCount();

            this.puchaseList = mapToHistoryList(purchaseHistories.getPuchaseList());


        }
        private List<PuchaseHistoryDto.PuchaseHistorySummary> mapToHistoryList(List<PuchaseHistoryInfo.Main> puchaseList) {
            if (puchaseList == null || puchaseList.isEmpty()) {
                return Collections.emptyList();
            }


            return puchaseList.stream()
                    .map(this::mapToHistory) // 개별 매핑 로직
                    .toList();
        }

        private PuchaseHistorySummary mapToHistory(PuchaseHistoryInfo.Main puchaseList) {
            return PuchaseHistorySummary.builder()
                    .historyId(puchaseList.getHistoryId())
                    .purchasedDateTime(puchaseList.getPurchasedDateTime())
                    .itemName(puchaseList.getItemName())
                    .productName(puchaseList.getProductName())
                    .price(puchaseList.getPrice())
                    .payment(puchaseList.getPayment())
                    .build();
        }


    }

    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    public static class PuchaseHistorySummary {

        private Long historyId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime purchasedDateTime;

        private String itemName;

        private String productName;

        private BigDecimal price;

        private euljiro.project.childcareproducts.domain.group.history.PuchaseHistory.PAYMENT payment;


        public static PuchaseHistoryDto.PuchaseHistorySummary from(PuchaseHistoryInfo.Main main) {
            return PuchaseHistoryDto.PuchaseHistorySummary.builder()
                    .historyId(main.getHistoryId())
                    .purchasedDateTime(main.getPurchasedDateTime())
                    .itemName(main.getItemName())
                    .productName(main.getProductName())
                    .price(main.getPrice())
                    .payment(main.getPayment())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
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

        private String cardName;

        private String cardNumberSuffix;

        private Card.Company company;

        public static PuchaseHistory from(PuchaseHistoryInfo.Main main) {
            return PuchaseHistory.builder()
                    .historyId(main.getHistoryId())
                    .purchasedDateTime(main.getPurchasedDateTime())
                    .itemName(main.getItemName())
                    .category(main.getCategory())
                    .productName(main.getProductName())
                    .purchaseRoute(main.getPurchaseRoute())
                    .productStatus(main.getProductStatus())
                    .price(main.getPrice())
                    .payment(main.getPayment())
                    .cardName(main.getCardName())
                    .cardNumberSuffix(main.getCardNumberSuffix())
                    .company(main.getCompany())
                    .build();
        }

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

    @Getter
    @ToString
    public static class GetPurchaseHistoryMain {
        LocalDate selectedMonth;                    // 선택월

        private int selectedMonthTotalCount;        // 선택월전체건수

        private int selectedMonthPurchaseCount;     // 선택월구매건수

        private int selectedMonthShareCount;        // 선택월나눔건수

        private BigDecimal purchaseAmountMonth0;    // 선택월 총금액

        private BigDecimal purchaseAmountMonth1;    // 선택월-1 개월전 총금액

        private BigDecimal purchaseAmountMonth2;    // 선택월-2 개월전 총금액

        private BigDecimal purchaseAmountMonth3;    // 선택월-3 개월전 총금액

        private BigDecimal purchaseAmountMonth4;    // 선택월-4 개월전 총금액

        private BigDecimal purchaseAmountMonth5;    // 선택월-5 개월전 총금액

        private List<PuchaseHistory>  recentPurchaseHistory; // 최근거래이력 5개
        public GetPurchaseHistoryMain(PuchaseHistoryInfo.GetMainResponse response) {
            this.selectedMonth = response.getSelectedMonth();
            this.selectedMonthTotalCount = response.getSelectedMonthTotalCount();
            this.selectedMonthPurchaseCount = response.getSelectedMonthPurchaseCount();
            this.selectedMonthShareCount = response.getSelectedMonthShareCount();
            this.purchaseAmountMonth0 = response.getPurchaseAmountMonth0();
            this.purchaseAmountMonth1 = response.getPurchaseAmountMonth1();
            this.purchaseAmountMonth2 = response.getPurchaseAmountMonth2();
            this.purchaseAmountMonth3 = response.getPurchaseAmountMonth3();
            this.purchaseAmountMonth4 = response.getPurchaseAmountMonth4();
            this.purchaseAmountMonth5 = response.getPurchaseAmountMonth5();
            this.recentPurchaseHistory = response.getRecentPurchaseHistory()
                    .stream()
                    .map(PuchaseHistoryDto.PuchaseHistory::from)
                    .toList();


        }
    }
}

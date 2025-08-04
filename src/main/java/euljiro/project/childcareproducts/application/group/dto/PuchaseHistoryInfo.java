package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.product.Product;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.MonthlyAmountDto;
import euljiro.project.childcareproducts.infrastructure.group.history.dto.SelectedMonthStatsDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PuchaseHistoryInfo {


    @Getter
    @ToString
    public static class GetMainResponse {

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

        private List<Main>  recentPurchaseHistory; // 최근거래이력 5개


        public GetMainResponse(SelectedMonthStatsDto monthlyPurchaseStats, List<MonthlyAmountDto> pastFiveMonthsAmounts, List<PuchaseHistory> top5RecentPurchaseHistories) {
            this.selectedMonth = monthlyPurchaseStats.getMonth();
            this.selectedMonthTotalCount = monthlyPurchaseStats.getTotalCount();
            this.selectedMonthPurchaseCount = monthlyPurchaseStats.getPurchaseCount();
            this.selectedMonthShareCount = monthlyPurchaseStats.getShareCount();
            this.purchaseAmountMonth0 = monthlyPurchaseStats.getPurchaseAmount();

            // 초기화: 없으면 0으로
            this.purchaseAmountMonth1 = BigDecimal.ZERO;
            this.purchaseAmountMonth2 = BigDecimal.ZERO;
            this.purchaseAmountMonth3 = BigDecimal.ZERO;
            this.purchaseAmountMonth4 = BigDecimal.ZERO;
            this.purchaseAmountMonth5 = BigDecimal.ZERO;

            // pastFiveMonthsAmounts에서 각 월별 구매금액을 필드에 맞게 세팅
            for (MonthlyAmountDto dto : pastFiveMonthsAmounts) {
                long monthsDiff = java.time.temporal.ChronoUnit.MONTHS.between(dto.getMonth(), this.selectedMonth);
                switch ((int) monthsDiff) {
                    case 1 -> this.purchaseAmountMonth1 = dto.getPurchaseAmount();
                    case 2 -> this.purchaseAmountMonth2 = dto.getPurchaseAmount();
                    case 3 -> this.purchaseAmountMonth3 = dto.getPurchaseAmount();
                    case 4 -> this.purchaseAmountMonth4 = dto.getPurchaseAmount();
                    case 5 -> this.purchaseAmountMonth5 = dto.getPurchaseAmount();
                }
            }

            // 최근 구매이력 변환
            this.recentPurchaseHistory = top5RecentPurchaseHistories.stream()
                    .map(Main::new)
                    .toList();
        }
    }
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

        private String cardName;

        private String cardNumberSuffix;

        private Card.Company company;

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
            this.cardName = puchaseHistory.getCardName();
            this.cardNumberSuffix = puchaseHistory.getCardNumberSuffix();
            this.company = puchaseHistory.getCompany();
        }
    }

}

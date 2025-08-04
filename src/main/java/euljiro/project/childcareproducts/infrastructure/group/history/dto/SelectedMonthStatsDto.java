package euljiro.project.childcareproducts.infrastructure.group.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class SelectedMonthStatsDto {

    LocalDate month;                            // 선택월

    private int totalCount;                     // 전체건수

    private int purchaseCount;                  // 구매건수

    private int shareCount;                     // 월나눔건수

    private BigDecimal purchaseAmount;          // 월 총금액
}

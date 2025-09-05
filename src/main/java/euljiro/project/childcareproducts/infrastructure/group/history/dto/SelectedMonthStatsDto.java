package euljiro.project.childcareproducts.infrastructure.group.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SelectedMonthStatsDto {

    private String month;                            // 선택월

    private Long totalCount;                     // 전체건수

    private Long purchaseCount;                  // 구매건수

    private Long shareCount;                     // 월나눔건수

    private BigDecimal purchaseAmount;          // 월 총금액
}

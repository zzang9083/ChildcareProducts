package euljiro.project.childcareproducts.infrastructure.group.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.YearMonth;


@AllArgsConstructor
@Getter
public class MonthlyAmountDto {
    private YearMonth month; // or YearMonth if preferred
    private BigDecimal purchaseAmount;
}

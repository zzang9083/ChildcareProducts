package euljiro.project.childcareproducts.infrastructure.group.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@AllArgsConstructor
@Getter
public class MonthlyAmountDto {
    private String month; // or YearMonth if preferred
    private BigDecimal purchaseAmount;
}

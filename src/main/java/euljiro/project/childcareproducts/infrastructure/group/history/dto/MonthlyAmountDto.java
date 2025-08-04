package euljiro.project.childcareproducts.infrastructure.group.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@Getter
public class MonthlyAmountDto {
    private LocalDate month; // or YearMonth if preferred
    private BigDecimal purchaseAmount;
}

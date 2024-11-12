package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class ItemInfo {

    @Getter
    @Builder
    @ToString
    public static class Main {

        private String itemToken;

        private String groupToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private Item.Status status;
    }


}

package euljiro.project.childcareproducts.api.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.item.Item;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemDto {



    @Getter
    @Builder
    @ToString
    public static class UpdateItemRequest {

        @NotEmpty(message = "itemName는 필수값입니다")
        private String itemName;

        @NotNull(message = "category는 필수값입니다")
        @ValidEnum(enumClass = Item.Category.class)
        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        @NotNull(message = "itemStatus는 필수값입니다")
        @ValidEnum(enumClass = Item.ItemStatus.class)
        private Item.ItemStatus itemStatus;

        private String description;

        public ItemCommand.UpdateItemRequest toCommand(String itemToken) {
            return ItemCommand.UpdateItemRequest.builder()
                    .itemToken(itemToken)
                    .itemName(this.itemName)
                    .category(this.category)
                    .minPrice(this.minPrice)
                    .maxPrice(this.maxPrice)
                    .itemStatus(this.itemStatus)
                    .description(this.description).build();
        }

    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor // Jackson이 이걸로 객체 생성
    @AllArgsConstructor // Builder 쓸 경우 필요함
    public static class ChangeStatusRequest {

//        @NotNull(message = "status는 필수값입니다")
//        @ValidEnum(enumClass = Item.Status.class)
//        @JsonProperty("status")
//        private Item.Status status;
        private String status;


        public ItemCommand.ChangeStatusRequest toCommand(String itemToken) {
            return ItemCommand.ChangeStatusRequest.builder()
                    .itemToken(itemToken)
                    .status(convertToEnum(this.status))
                    .build();
        }

        private Item.Status convertToEnum(String value) {
            try {
                return Item.Status.valueOf(value.toUpperCase()); // 대소문자 무시
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new IllegalArgumentException("잘못된 status 값입니다: " + value);
            }
        }


    }


    @Getter
    @Builder
    @ToString
    public static class RegisterItemResponse {

        private String groupToken;

    }

    @Getter
    @Builder
    @ToString
    public static class GetItemResponse {

        private String itemToken;

        private String itemName;

        private Item.Category category;

        private BigDecimal minPrice;

        private BigDecimal maxPrice;

        private Item.ItemStatus itemStatus;

        private String description;

        private Item.Status status;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime creationTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updateTime;

    }



}

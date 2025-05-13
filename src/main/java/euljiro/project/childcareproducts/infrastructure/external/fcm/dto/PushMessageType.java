package euljiro.project.childcareproducts.infrastructure.external.fcm.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PushMessageType {
    //ITEM_REGISTERED("새 아이템 등록", "%s 아이템이 등록되었습니다."),
    MATCH_COMPLETE("매치 완료", "그룹 매치가 완료되었습니다.") ,
    PRODUCT_PURCHASED("구매 완료", "%s 제품이 구매완료 처리되었습니다.");

    private final String title;
    private final String bodyTemplate;

    public String getBody(Object... args) {
        return String.format(bodyTemplate, args);
    }
}

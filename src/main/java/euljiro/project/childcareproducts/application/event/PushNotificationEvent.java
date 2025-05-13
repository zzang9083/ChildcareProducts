package euljiro.project.childcareproducts.application.event;

import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.PushMessageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PushNotificationEvent {
    private final String userKey; // 푸시 보낼 대상
    private final PushMessageType messageType; // 알림 종류
    private final Object[] messageArgs; // 템플릿 파라미터

    public PushNotificationEvent(String userKey, PushMessageType messageType) {
        this.userKey = userKey;
        this.messageType = messageType;
        this.messageArgs = new Object[0];
    }
}

package euljiro.project.childcareproducts.infrastructure.external.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.FcmMessageRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FcmUtil {
    Message buildMessage(FcmMessageRequestDto dto) {
        Notification notification = Notification.builder()
                .setTitle(dto.getTitle())
                .setBody(dto.getBody())
                .build();


        return Message.builder()
                .setToken(dto.getToken())
                .setNotification(notification) // ✨ 알림 설정 추
                .putData("title", dto.getTitle())
                .putData("content", dto.getBody())
                .build();
    }

    public void sendMessage(FcmMessageRequestDto dto) {
        try {
            FirebaseMessaging.getInstance().send(buildMessage(dto));
        } catch (FirebaseMessagingException ex) {
            log.error("Failed to send FCM message: {}", ex.getMessage(), ex);
            log.error("Failed to send FCM message. token={}, title={}, body={}",
                    dto.getToken(), dto.getTitle(), dto.getBody(), ex);
        }
    }
}

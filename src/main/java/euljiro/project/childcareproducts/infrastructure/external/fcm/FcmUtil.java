package euljiro.project.childcareproducts.infrastructure.external.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.FcmMessageRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FcmUtil {
    Message buildMessage(FcmMessageRequestDto dto) {
        return Message.builder()
                .putData("tiltle", dto.getTitle())
                .putData("content", dto.getBody())
                .setToken(dto.getToken())
                .build();
    }

    public void sendMessage(FcmMessageRequestDto dto) {
        try {
            FirebaseMessaging.getInstance().send(buildMessage(dto));
        } catch (FirebaseMessagingException ex) {
            log.error("Failed to Message send");
        }
    }
}

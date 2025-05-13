package euljiro.project.childcareproducts.application.event;

import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import euljiro.project.childcareproducts.infrastructure.external.fcm.FcmUtil;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.FcmMessageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PushNotificationEventListener {

    private final UserService userService;
    private final FcmMessageFactory fcmMessageFactory;
    private final FcmUtil fcmUtil;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PushNotificationEvent event) {
        log.debug("PushNotificationEventListener.handle start :: event : {}", event);
        String userKey = event.getUserKey();
        User user = userService.getUser(userKey);

        if (user.getPushToken() == null) return;

        FcmMessageRequestDto message = fcmMessageFactory.create(
                user.getPushToken(),
                event.getMessageType(),
                event.getMessageArgs()
        );

        fcmUtil.sendMessage(message);

        log.debug("PushNotificationEventListener.handle end");
    }
}

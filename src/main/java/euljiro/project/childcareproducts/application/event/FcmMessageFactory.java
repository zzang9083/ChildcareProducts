package euljiro.project.childcareproducts.application.event;


import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.FcmMessageRequestDto;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.PushMessageType;

public class FcmMessageFactory {

    public FcmMessageRequestDto create(String token, PushMessageType type, Object... args) {
        return new FcmMessageRequestDto(
                token,
                type.getTitle(),
                type.getBody(args)
        );
    }
}

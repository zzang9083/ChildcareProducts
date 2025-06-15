package euljiro.project.childcareproducts.application.complex;


import euljiro.project.childcareproducts.application.complex.dto.GroupMatchCommand;
import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import euljiro.project.childcareproducts.application.event.PushNotificationEvent;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCodeService;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.PushMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GroupMatchService {

    private final ShareCodeService shareCodeService;
    private final GroupService groupService;
    private final ApplicationEventPublisher eventPublisher;

    //    private final FcmMessageFactory fcmMessageFactory;
    //
    //    private final FcmUtil fcmUtil;



    public GroupMatchInfo.MatchGroupResponse matchGroup(GroupMatchCommand.MatchGroupRequest command) {
        var inputShareCode = command.getShareCode();
        var inputUserKey = command.getUserKey();

        log.info("***** GroupMatchService.matchGroup input *****");
        log.info("inputShareCode:"+ inputShareCode);
        log.info("inputUserKey"+ inputUserKey);
        log.info("***********************************************");

        // 공유코드의 주인 userKey
        var ownerUserKey
                = shareCodeService.getUserKeyByShareCode(inputShareCode);
        log.info("ownerUserKey:" + ownerUserKey);

        // 그룹매칭
        GroupMatchInfo.MatchGroupResponse response
            = groupService.matchGroup(ownerUserKey, inputUserKey);

        log.info("***** GroupMatchService.matchGroup end *****");


        // push메시지 발송
        eventPublisher.publishEvent(
                new PushNotificationEvent(ownerUserKey, PushMessageType.MATCH_COMPLETE)
        );


        return response;
    }
}

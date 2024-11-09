package euljiro.project.childcareproducts.application.complex;


import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import euljiro.project.childcareproducts.application.complex.dto.GroupCommand;
import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.user.sharecode.ShareCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GroupMatchService {

    private final ShareCodeService shareCodeService;

    private final GroupService groupService;

    private final UserService userService;


    public GroupInfo.MatchGroupResponse matchGroup(GroupCommand.MatchGroupRequest command) {
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
        GroupInfo.MatchGroupResponse matchGroupResponse
            = groupService.matchGroup(ownerUserKey, inputUserKey);


        return matchGroupResponse;
    }
}

package euljiro.project.childcareproducts.domain.user.group;

import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final UserReader userReader;

    private final GroupStore groupStore;

    @Override
    public GroupInfo.MatchGroupResponse matchGroup(String inputUserKey, String ownerUserKey) {

        // 회원 불러오기
        var inputUser = userReader.getUserByUserkey(inputUserKey);
        var ownerUser = userReader.getUserByUserkey(ownerUserKey);

        // 그룹생성
        Group initGroup = groupStore.store(new Group());

        // 회원 그룹매칭
        inputUser.matchComplete(initGroup);
        ownerUser.matchComplete(initGroup);

        return new GroupInfo.MatchGroupResponse(initGroup);
    }
}

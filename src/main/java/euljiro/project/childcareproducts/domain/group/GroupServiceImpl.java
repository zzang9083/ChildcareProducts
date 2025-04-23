package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildReader;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {


    private final GroupStore groupStore;

    private final GroupReader groupReader;

    private final UserReader userReader;
    private final ChildReader childReader;

    @Override
    public Group getGroupBy(String groupToken) {
        return groupReader.findByGroupToken(groupToken);
    }

//    @Override
//    public Group getDashBoardInfo(String groupToken) {
//        return groupReader.getDashBoardInfo(groupToken);
//    }

    @Override
    public GroupMatchInfo.MatchGroupResponse matchGroup(String ownerUserKey, String inputUserKey) {

        log.info("***** GroupServiceImpl.matchGroup start *****");

        User ownerUser = userReader.getUserAndGroupByUserkey(ownerUserKey);
        User joinUser = userReader.getUserAndGroupByUserkey(inputUserKey);

        Group ownergroup = ownerUser.getGroup();

        // 신규 그룹
        if(ownergroup == null) {

            //그룹 유저정보 불러오기
            List<User> groupingUserList = new ArrayList<User>();
            groupingUserList.add(ownerUser); // 그룹장 user
            groupingUserList.add(joinUser); // 입력 user

            //상태체크
            for (User user : groupingUserList) {
                user.checkValidStatus();
            }

            // 아이정보 불러오기(owner기준)
            List<Child> childList = childReader.getAllActiveChildBy(ownerUserKey);

            // 그룹초기화 및 생성
            Group initGroup = new Group(groupingUserList, childList);
            Group group = groupStore.store(initGroup);

            return new GroupMatchInfo.MatchGroupResponse(group);
        }
        // 기존 그룹
        else {
            // 기존 그룹에 포함시키기
            ownergroup.joinUser(joinUser);
            // 오너 상태변경
            ownerUser.changeStatusAtMatched();

            groupStore.store(ownergroup);

            return new GroupMatchInfo.MatchGroupResponse(ownergroup);

        }

    }

    @Override
    public GroupCardInfo.GetCardsResponse getCardsByGroupToken(String groupToken) {
        Group group = groupReader.findByCardsByGroupToken(groupToken);

        return new GroupCardInfo.GetCardsResponse(group);
    }

    public void updateStatus(Group group, Group.Status status) {
        if(Group.Status.INACTIVE == status) {
            //그룹 비활성화
            group.setInactiveStatus();
            groupStore.store(group);
        }
    }

    @Override
    public void changeSelectedChild(String groupToken, long childId) {
        Group group = groupReader.findByGroupToken(groupToken);

        group.changeSelectedChild(childId);

        groupStore.store(group);
    }
}

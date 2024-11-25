package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
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

    private final UserReader userReader;

    private final GroupStore groupStore;

    private final ChildReader childReader;

    @Override
    public GroupInfo.MatchGroupResponse matchGroup(String ownerUserKey, String inputUserKey) {

        //그룹 유저정보 불러오기
        List<User> groupingUserList= new ArrayList<User>();
        groupingUserList.add(userReader.getUserByUserkey(ownerUserKey)); // 그룹장 user
        groupingUserList.add(userReader.getUserByUserkey(inputUserKey)); // 입력 user

        //상태체크
        for(User user : groupingUserList) {
            user.checkValidStatus();
        }

        // 아이정보 불러오기(owner기준)
        List<Child> childList = childReader.getAllChildBy(ownerUserKey);

        // 그룹초기화 및 생성
        Group initGroup = new Group(groupingUserList, childList);
        Group group = groupStore.store(initGroup);


        return new GroupInfo.MatchGroupResponse(group);
    }
}

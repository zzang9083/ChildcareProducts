package euljiro.project.childcareproducts.application.user;


import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserGroupService {


    private final UserService userService;

    private final GroupService groupService;


    public void withdrawUser(String userKey) {
        // 사용자+그룹 조회
        User user = userService.getUserAndGroup(userKey);

        // 사용자 탈퇴
        userService.updateStatus(user, User.Status.WITHDRAW);

        // 그룹
        Group group = user.getGroup();
        if(group != null) {
            // 그룹에 남은 사용자가 없으면 그룹상태 변경
            if(userService.isGroupEmpty(group.getId())) {
                groupService.updateStatus(group, Group.Status.INACTIVE);
            }
            // 그룹에 남은 사용자가 1명 있으면 남은 사용자 상태 변경
            else {
                updateStatusExtraUser(group);
            }
        }

    }

    private void updateStatusExtraUser(Group group) {
        User remainingUser = group.getUserList().stream()
                .filter(u -> u.getStatus() == User.Status.MATCHED)
                .findFirst()
                .orElse(null);
        if (remainingUser != null) {
            userService.updateStatus(remainingUser, User.Status.MATCHING);
        }
    }
}

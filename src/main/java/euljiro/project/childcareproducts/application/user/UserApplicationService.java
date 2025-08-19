package euljiro.project.childcareproducts.application.user;

import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import euljiro.project.childcareproducts.domain.user.selectedChild.SelectedChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserApplicationService {

    private final UserService userService;

    private final SelectedChildService selectedChildService;

    private final ChildService childService;


    public UserInfo.UserInfoRegisterResponse registerUserInfo(UserCommand.RegisterUserInfoRequest userCommand) {
        log.debug("UserApplicationService.registerUserInfo start");

        // 사용자 조회
        User user = userService.getUser(userCommand.getUserKey());
        // 사용자 정보 등록
        User updatedUser = userService.registerUserInfo(user, userCommand);

        log.debug("UserApplicationService.registerUserInfo end");
        return new UserInfo.UserInfoRegisterResponse(updatedUser);

    }

    public long changeSelectedChild(String userKey, String childToken) {

        Child child = childService.getChildBy(childToken);

        selectedChildService.storeSelectedChild(userKey, child.getId());


        return child.getId();

    }


}

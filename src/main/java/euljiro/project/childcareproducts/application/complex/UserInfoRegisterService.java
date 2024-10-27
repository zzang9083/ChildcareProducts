package euljiro.project.childcareproducts.application.complex;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.complex.dto.UserRegisterInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoRegisterService {

    private final euljiro.project.childcareproducts.domain.user.UserService userService;

    private final ChildService childService;



    public UserRegisterInfo.UserInfoRegisterResponse registerUserInfo(UserCommand.RegisterUserInfoRequest userCommand, ChildCommand childCommand) {

        // 사용자 조회
        User user = userService.getUser(userCommand.getUserKey());
        // 사용자 정보 등록
        User updatedUser = userService.registerUserInfo(user, userCommand);

        // 아기 정보 등록
        Child registeredChild = childService.registerChildInfo(childCommand);

        return new UserRegisterInfo.UserInfoRegisterResponse(updatedUser, registeredChild);

    }





}

package euljiro.project.childcareproducts.application.user;

import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoRegisterService {

    private final euljiro.project.childcareproducts.domain.user.UserService userService;


//    public UserInfo.RegisterResponse registerUserInfo(UserCommand.RegisterRequest command) {
//
//        // 사용자 조회
//        User user = userService.getUser(command.getUserKey());
//
//        // 사용자 정보 등록
//        userService.registerUserInfo(user, command.getNickname(), command.getRelationship());
//
//        // 아기 정보 등록
//        //childService.registerChildInfo(command);
//
//    }





}

package euljiro.project.childcareproducts.domain.user;

import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStore userStore;

    private final UserReader userReader;

    @Override
    @Transactional
    public LoginInfo registerUser(UserCommand command) {
        var initUser = command.toEntity();
        User user = userStore.store(initUser);
        return new LoginInfo(user);
    }

    @Override
    public User getUser(String userKey) {

        User user = userReader.getUserByUserkey(userKey);

        // 사용자 상태체크
        user.checkValidStatus();

        return user;

    }

    @Override
    public User getUserOrRegister(String userKey) {
        User user;
        try {
            user = userReader.getUserByUserkey(userKey);
        } catch (EntityNotFoundException e) {
            log.info("EntityNotFoundException");
            user = new User(userKey);
            userStore.store(user);
        }

        return user;
    }

    public User registerUserInfo(User user, UserCommand.RegisterUserInfoRequest userCommand) {
        log.info("UserKey : "+ userCommand.getUserKey());
        log.info("Nickname : "+ userCommand.getNickname());


        user.checkValidStatus();


        user.registerUserInfo(userCommand);
        user = userStore.store(user);

        return user;
    }

}

//    @Override
//    public List<UserInfo> getUserInfoList(List<String> userTokenList) {
//        List<User> users = userReader.getUserList(userTokenList);
//
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//
//        for(User user: users) {
//            userInfoList.add(new UserInfo(user));
//        }
//
//        return userInfoList;
//    }

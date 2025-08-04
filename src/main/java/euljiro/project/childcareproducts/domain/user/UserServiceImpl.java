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
    public User getUserBy(long userId) {
        User user = userReader.getUserById(userId);

        // 사용자 상태체크
        user.checkValidStatus();

        return user;
    }

    @Override
    public User getUserAndGroup(String userKey) {

        User user = userReader.getUserAndGroupByUserkey(userKey);

        // 사용자 상태체크
        user.checkValidStatus();

        return user;

    }



    @Override
    public User getUserOrRegister(String userKey, String pushToken) {
        User user;
        try {
            user = userReader.getUserAndGroupByUserkey(userKey);
            log.info("UserServiceImpl.getUserOrRegister get case");
        } catch (EntityNotFoundException e) {
            log.info("UserServiceImpl.getUserOrRegister register case");
            user = new User(userKey, pushToken);
            userStore.store(user);
        }

        return user;
    }

    public User registerUserInfo(User user, UserCommand.RegisterUserInfoRequest userCommand) {

        // 사용자 상태점검
        user.checkValidStatus();

        // 사용자 정보 등록
        user.registerUserInfo(userCommand);
        user = userStore.store(user);

        return user;
    }

    public void updateStatus(User user, User.Status status) {
        if(User.Status.WITHDRAW == status) {
            //사용자 탈퇴
            user.withdraw();
        }
        else if(User.Status.MATCHING == status) {
            //사용자 매칭중
            user.changeStatusAtMatching();
        }
        userStore.store(user);

    }

    @Override
    public boolean isGroupEmpty(Long groupId) {
        return userReader.countByGroupId(groupId) == 0;
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


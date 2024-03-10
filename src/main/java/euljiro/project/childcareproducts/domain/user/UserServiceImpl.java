package euljiro.project.childcareproducts.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserStore userStore;

    private final UserReader userReader;

    @Override
    @Transactional
    public UserInfo registerUser(UserCommand command) {
        var initUser = command.toEntity();
        User user = userStore.store(initUser);
        return new UserInfo(user);
    }

    @Override
    public UserInfo getUserInfo(String userKey) {
        User user = userReader.getUserByUserkey(userKey);

        return new UserInfo(user);
    }

    @Override
    public void checkValidUser(String userKey) {

        // 사용자 조회
        User user = userReader.getUserByUserkey(userKey);

        // 사용자 상태체크
        if(user.isValidStatus()) {
            throw new IllegalStateException("이미 탈퇴한 회원의 정보 및 요청입니다");
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
}

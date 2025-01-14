package euljiro.project.childcareproducts.infrastructure.user;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

//    @Override
//    public User getUserById(String id) {
//        return userRepository.findById(id)
//                .orElseThrow(EntityNotFoundException::new);
//    }

    @Override
    public User getUserByUserkey(String userkey) {
        return userRepository.findByUserKey(userkey)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자입니다."));
    }

    @Override
    public User getUserAndGroupByUserkey(String userkey) {
        return userRepository.getUserAndGroupByUserkey(userkey)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자입니다."));
    }

    //    @Override
//    public List<User> getUserList(List<String> userTokenList) {
//        return userRepository.findByUserTokenIn(userTokenList);
//    }
}

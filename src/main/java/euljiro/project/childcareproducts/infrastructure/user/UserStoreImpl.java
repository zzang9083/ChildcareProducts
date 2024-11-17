package euljiro.project.childcareproducts.infrastructure.user;


import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    @Override
    public User store(User user) {
        if (StringUtils.isEmpty(user.getUserKey())) throw new InvalidParamException("empty userKey");
        if (user.getStatus() == null) throw new InvalidParamException("empty status");
        return userRepository.save(user);
    }
}

package euljiro.project.childcareproducts.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserReader userReader;

    @Override
    public UserDetails loadUserByUsername(String userKey) throws UsernameNotFoundException {
        return userReader.getUserByUserkey(userKey);
    }
}

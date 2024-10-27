package euljiro.project.childcareproducts.domain.user;


import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.domain.group.Group;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String userKey; // 카카오 유저번호

    private Gender gender;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @Getter
    @RequiredArgsConstructor
    public enum Status {
        IN_PROGRESS("매치진행중"), MATCHED("그룹매치완료"), WITHDRAW("탈퇴");
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Gender {
        MALE("남성"), FEMALE("여성");
        private final String description;
    }


    @Builder
    public User(String userKey) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.userKey = userKey;
        this.status = Status.IN_PROGRESS;
    }


    @Builder
    public User(String userKey,String nickName, Gender gender) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.userKey = userKey;
        this.nickName = nickName;
        this.gender = gender;
        this.status = Status.IN_PROGRESS;
    }

    public void matchGroup(Group group) {
        if(this.status != Status.IN_PROGRESS) {
            log.info("this.status:" + this.status);
            throw new IllegalStateException();
        }
        this.status = Status.MATCHED;
        this.group = group;
        group.getUserList().add(this);

    }

    public void withdraw() {
        if(this.status == Status.WITHDRAW) throw new IllegalStateException();
        this.status = Status.WITHDRAW;
    }

    public boolean isValidStatus() {
        if(this.status == Status.WITHDRAW) return false;

        return true;
    }

    public void registerUserInfo(UserCommand.RegisterUserInfoRequest userCommand) {
        this.nickName = nickName;
        this.status = status;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userKey;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

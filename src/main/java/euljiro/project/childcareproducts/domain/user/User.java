package euljiro.project.childcareproducts.domain.user;


import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.common.exception.IllegalStatusException;
import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.domain.AbstractEntity;
import euljiro.project.childcareproducts.domain.group.Group;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "users", indexes = @Index(name = "idx_userKey", columnList = "userKey", unique = true))
public class User extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String userKey; // 카카오 유저번호

    @Enumerated(EnumType.STRING)
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
        SIGNING_UP("가입중"), MATCHING("매치진행중"), MATCHED("그룹매치완료"), WITHDRAW("탈퇴");
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
        this.status = Status.SIGNING_UP;
    }


    @Builder
    public User(String userKey,String nickName, Gender gender) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.userKey = userKey;
        this.nickName = nickName;
        this.gender = gender;
        this.status = Status.SIGNING_UP;
    }

    public void matchGroup(Group group) {
        if(this.status != Status.MATCHING) {
            log.info("this.status:" + this.status);
            throw new IllegalStateException();
        }
        this.status = Status.MATCHED;
        this.group = group;
        group.getUserList().add(this);

    }

    public void withdraw() {
        this.status = Status.WITHDRAW;
        this.group.removeUser(this);
    }

    public void changeStatusAtMatching() {
        this.status = Status.MATCHING;
    }

    public void changeStatusAtMatched() {
        this.status = Status.MATCHED;
    }

    public void checkValidStatus() {
        if(this.status == Status.WITHDRAW) throw new IllegalStatusException("유효하지 않은 사용자 상태입니다. 사용자 현재 상태 :"+ this.status.toString());

    }

    public void registerUserInfo(UserCommand.RegisterUserInfoRequest userCommand) {
        if (StringUtils.isEmpty(userKey)) throw new InvalidParamException("empty userKey");

        this.userKey = userCommand.getUserKey();
        this.nickName = userCommand.getNickname();
        this.gender = userCommand.getGender();
        this.status = Status.MATCHING;

    }

    public void joinGroup(Group group) {
        this.group = group;
        this.status = Status.MATCHED;
    }

}

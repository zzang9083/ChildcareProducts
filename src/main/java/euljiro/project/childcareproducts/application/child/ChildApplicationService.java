package euljiro.project.childcareproducts.application.child;


import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChildApplicationService {

    private final ChildService childService;

    private final TokenUtil tokenUtil;

    public ChildInfo.ChildRegisterResponse registerChild(ChildCommand childCommand) {

        log.info("ChildCommand.getUserKey():" + childCommand.getRegisteredUserKey());

        // 아기 정보 등록
        Child registeredChild = childService.registerChildInfo(childCommand);

        tokenUtil.storeIdByToken(registeredChild.getChildToken(), registeredChild.getId());

        return new ChildInfo.ChildRegisterResponse(registeredChild);
    }
}

package euljiro.project.childcareproducts.application.child;


import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildService;
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

    public ChildInfo.ChildRegisterResponse registerChildByUser(ChildCommand.RegisterChildByUserRequest childCommand) {

        log.info("ChildApplicationService.registerChild start :: input : {}", childCommand);

        // 아기 정보 등록
        Child registeredChild = childService.registerChildByUser(childCommand);

        log.info("ChildApplicationService.registerChild end");

        return new ChildInfo.ChildRegisterResponse(registeredChild);
    }

    public String deleteChild(String childToken) {
        childService.deleteChild(childToken);

        return childToken;

    }
}

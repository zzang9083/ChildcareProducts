package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildStore childStore;

    private final ChildReader childReader;


    public Child registerChildInfo(ChildCommand childCommand) {
        var initChild = childCommand.toEntity();
        return childStore.store(initChild);
    }

    @Override
    public Child getChildBy(long childId) {
        return childReader.getChildBy(childId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이정보입니다."));
    }
}

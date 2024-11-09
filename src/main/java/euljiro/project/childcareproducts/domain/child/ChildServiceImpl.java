package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildStore childStore;


    public Child registerChildInfo(ChildCommand childCommand) {
        var initChild = childCommand.toEntity();
        return childStore.store(initChild);
    }
}

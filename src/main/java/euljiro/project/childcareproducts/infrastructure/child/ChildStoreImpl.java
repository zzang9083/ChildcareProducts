package euljiro.project.childcareproducts.infrastructure.child;

import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildStore;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChildStoreImpl implements ChildStore {

    private final ChildRepository childRepository;

    @Override
    public Child store(Child child) {

        if (StringUtils.isEmpty(child.getChildName())) throw new InvalidParamException("empty childName");
        if (StringUtils.isEmpty(child.getStatus().toString())) throw new InvalidParamException("empty status");
        if (StringUtils.isEmpty(child.getBirthDate().toString())) throw new InvalidParamException("empty birthday");

        return childRepository.save(child);
    }
}

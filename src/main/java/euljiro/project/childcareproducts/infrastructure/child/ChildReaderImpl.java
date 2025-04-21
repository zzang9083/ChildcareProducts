package euljiro.project.childcareproducts.infrastructure.child;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.child.ChildReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChildReaderImpl implements ChildReader {

    private final ChildRepository childRepository;


    public List<Child> getAllActiveChildBy(String userKey) {
        return childRepository.findAllByRegisteredUserKeyAndStatus(userKey, Child.Status.ACTIVE);
    }

    @Override
    public Child getChildBy(long childId) {
        return childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이정보입니다."));
    }

    @Override
    public Child getChildBy(String childToken) {
        return childRepository.findByChildToken(childToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이정보입니다."));
    }
}

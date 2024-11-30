package euljiro.project.childcareproducts.infrastructure.group;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class GroupReaderImpl implements GroupReader {

    private final GroupRepository groupRepository;

    @Override
    public Group findByGroupToken(String groupToken) {
        return groupRepository.findByGroupToken(groupToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 그룹정보입니다."));
    }
}

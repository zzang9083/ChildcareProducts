package euljiro.project.childcareproducts.infrastructure.group;

import euljiro.project.childcareproducts.common.exception.InvalidParamException;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupStoreImpl implements GroupStore {

    private final GroupRepository groupRepository;

    @Override
    public Group store(Group group) {

        if (group.getUserList() == null) throw new InvalidParamException("empty UserList");
        if (group.getStatus() == null) throw new InvalidParamException("empty status");
        return groupRepository.save(group);

    }
}

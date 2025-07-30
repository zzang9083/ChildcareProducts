package euljiro.project.childcareproducts.infrastructure.user.selectedChild;

import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.user.selectedChild.SelectedChild;
import euljiro.project.childcareproducts.domain.user.selectedChild.SelectedChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SelectedChildUtil implements SelectedChildService {

    final SelectedChildRepository selectedChildRepository;

    @Override
    public void storeSelectedChild(String userKey, long childId) {
        selectedChildRepository.save(new SelectedChild(userKey, childId));
    }

    @Override
    public long getSelectedChildIdByUserKey(String userKey) {
        SelectedChild selectedChild = selectedChildRepository.findChildBy(userKey)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않는 공유코드입니다. 공유코드를 확인해주세요."));

        return selectedChildRepository.findChildBy(userKey).map(SelectedChild::getSelectedChildId).orElse(null);

    }
}

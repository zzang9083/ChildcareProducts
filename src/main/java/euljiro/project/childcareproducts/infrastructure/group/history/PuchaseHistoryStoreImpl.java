package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PuchaseHistoryStoreImpl implements PuchaseHistoryStore {

    private final PuchaseHistoryRepository puchaseHistoryRepository;

    @Override
    public PuchaseHistory store(PuchaseHistory puchaseHistory) {
        return puchaseHistoryRepository.save(puchaseHistory);
    }
}

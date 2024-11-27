package euljiro.project.childcareproducts.infrastructure.group.history;

import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryStore;

public class PuchaseHistoryStoreImpl implements PuchaseHistoryStore {

    PuchaseHistoryRepository puchaseHistoryRepository;

    @Override
    public PuchaseHistory store(PuchaseHistory puchaseHistory) {
        return puchaseHistoryRepository.save(puchaseHistory);
    }
}

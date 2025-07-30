package euljiro.project.childcareproducts.infrastructure.user.selectedChild;

import euljiro.project.childcareproducts.domain.user.selectedChild.SelectedChild;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SelectedChildRepository extends CrudRepository<SelectedChild, String> {

    public Optional<SelectedChild> findChildBy(String userKey);
}

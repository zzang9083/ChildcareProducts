package euljiro.project.childcareproducts.infrastructure.user.sharecode;

import euljiro.project.childcareproducts.domain.user.sharecode.ShareCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShareCodeRepository extends CrudRepository<ShareCode, String> {

    public Optional<ShareCode> findByUserKey(String userKey);


    public Optional<ShareCode> findByShareCode(String shareCode);


}

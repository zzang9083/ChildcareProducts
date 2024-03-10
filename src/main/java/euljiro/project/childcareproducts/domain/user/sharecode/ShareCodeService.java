package euljiro.project.childcareproducts.domain.user.sharecode;

public interface ShareCodeService {

    String generateShareCode(String userKey);

    String getUserKeyByShareCode(String shareCode);
}

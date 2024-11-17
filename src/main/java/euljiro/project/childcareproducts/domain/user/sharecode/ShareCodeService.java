package euljiro.project.childcareproducts.domain.user.sharecode;

public interface ShareCodeService {

    ShareCode generateShareCode(String userKey);

    String getUserKeyByShareCode(String shareCode);
}

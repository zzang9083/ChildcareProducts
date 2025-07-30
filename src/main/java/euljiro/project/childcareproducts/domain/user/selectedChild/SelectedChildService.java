package euljiro.project.childcareproducts.domain.user.selectedChild;

public interface SelectedChildService {

    void storeSelectedChild(String userKey, long childId);

    long getSelectedChildIdByUserKey(String userKey);
}

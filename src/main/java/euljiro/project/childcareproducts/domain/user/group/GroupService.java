package euljiro.project.childcareproducts.domain.user.group;

public interface GroupService {

    GroupInfo.MatchGroupResponse matchGroup(String inputUserKey, String ownerUserKey);
}

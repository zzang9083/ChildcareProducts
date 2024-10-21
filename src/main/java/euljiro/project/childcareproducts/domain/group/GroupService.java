package euljiro.project.childcareproducts.domain.group;

public interface GroupService {

    GroupInfo.MatchGroupResponse matchGroup(String inputUserKey, String ownerUserKey);
}

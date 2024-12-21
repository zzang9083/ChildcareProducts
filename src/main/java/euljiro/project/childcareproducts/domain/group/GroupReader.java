package euljiro.project.childcareproducts.domain.group;

public interface GroupReader {


    Group findByGroupId(long groupId);

    Group findByGroupToken(String groupToken);

    Group findByCardsByGroupToken(String groupToken);


}

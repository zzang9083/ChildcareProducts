package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.domain.group.card.Card;

public interface GroupReader {


    Group findByGroupId(long groupId);

    Group findByGroupToken(String groupToken);

    //Group getDashBoardInfo(String groupToken);

    Group findByCardsByGroupToken(String groupToken, Card.Status status);


}

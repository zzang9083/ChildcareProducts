package euljiro.project.childcareproducts.domain.group.card;


import euljiro.project.childcareproducts.application.group.dto.GroupCardCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements  CardService {

    private final CardStore cardStore;

    private final CardReader cardReader;


    @Override
    public GroupCardInfo.RegisterCardResponse registerCard(Group group, GroupCardCommand.RegisterCardRequest command) {
        var initCard = command.toEntity(group);
        Card card = cardStore.store(initCard);
        return new GroupCardInfo.RegisterCardResponse(card);
    }

    @Override
    public void disableCard(String cardToken) {
        Card card = cardReader.findByToken(cardToken);
        card.disable();

        cardStore.store(card);
    }
}

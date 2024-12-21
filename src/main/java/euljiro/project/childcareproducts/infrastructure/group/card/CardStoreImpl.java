package euljiro.project.childcareproducts.infrastructure.group.card;

import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.card.CardStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CardStoreImpl implements CardStore {

    private final CardRepsository cardRepsository;

    @Override
    public Card store(Card initCard) {

        return cardRepsository.save(initCard);
    }
}

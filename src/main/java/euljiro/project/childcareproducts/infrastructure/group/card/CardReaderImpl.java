package euljiro.project.childcareproducts.infrastructure.group.card;


import euljiro.project.childcareproducts.common.exception.EntityNotFoundException;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.card.CardReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardReaderImpl implements CardReader {

    private final CardRepsository cardRepsository;

    @Override
    public Card findByToken(String cardToken) {
        return cardRepsository.findByCardToken(cardToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 카드정보입니다."));
    }
}

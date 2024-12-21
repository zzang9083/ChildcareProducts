package euljiro.project.childcareproducts.domain.group.card;

import euljiro.project.childcareproducts.infrastructure.group.card.CardRepsository;

public interface CardReader { ;
    Card findByToken(String cardToken);
}

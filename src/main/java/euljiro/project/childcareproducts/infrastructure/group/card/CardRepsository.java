package euljiro.project.childcareproducts.infrastructure.group.card;

import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepsository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardToken(String cardToken);
}

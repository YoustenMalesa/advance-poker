package io.advance.poker.simulation.util;

import io.advance.poker.simulation.entity.Card;
import io.advance.poker.simulation.entity.CardDTO;

public class ObjectMapper {

    public CardDTO toCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setSuit(card.getSuit());
        cardDTO.setRank(card.getRank());

        return cardDTO;
    }

    public Card toCard(CardDTO cardDTO) {
        Card card = new Card(cardDTO.getRank(), cardDTO.getSuit());
        return card;
    }

}

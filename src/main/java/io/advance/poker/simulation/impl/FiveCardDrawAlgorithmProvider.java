package io.advance.poker.simulation.impl;

import io.advance.poker.simulation.api.PokerAlgorithmProvider;
import io.advance.poker.simulation.entity.*;
import io.advance.poker.simulation.util.ObjectMapper;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class FiveCardDrawAlgorithmProvider implements PokerAlgorithmProvider {
    private Deck deck = new Deck();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    private Hand hand;

    @Override
    public void shuffleDeck() {
        deck.shuffle();
    }

    @Override
    public HandDTO dealHand(int numberOfCards) {
        deck.shuffle();
        List<Card> cards = deck.dealHand(numberOfCards);
        HandDTO hand = new HandDTO();
        List<CardDTO> response = cards.stream().map(card ->
                objectMapper.toCardDTO(card)).collect(Collectors.toList());
        hand.setCards(response);
       return hand;
    }

    @Override
    public EvaluateResponse evaluateHand(HandDTO handDTO) {
        int handRanking = hand.evaluate(handDTO.getCards().stream().map(cardDTO ->
                objectMapper.toCard(cardDTO)).collect(Collectors.toList()));
        HandType handType = hand.rankHand(handRanking);
        EvaluateResponse evaluateResponse = new EvaluateResponse();
        evaluateResponse.setHandRanking(handType.getValue());
        evaluateResponse.setHandValue(handType.toString());

        return evaluateResponse;
    }

}



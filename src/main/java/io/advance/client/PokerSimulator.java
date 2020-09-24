package io.advance.client;

import io.advance.poker.simulation.entity.CardDTO;
import io.advance.poker.simulation.entity.EvaluateResponse;
import io.advance.poker.simulation.entity.HandDTO;

import java.util.stream.Collectors;

public class PokerSimulator {
    private static final AdvancePokerClient pokerClient = new AdvancePokerServiceClient();
    public static void main(String[] args) {
        try {
            System.out.println("shuffling....");
            shuffle();
            HandDTO handDTO = dealHand(5);
            String hand = handAsString(handDTO);
            System.out.println(hand);
            System.out.println(evaluateHand(handDTO));
        }catch (UnexpectedResponseException ure) {
            System.out.println(ure.getMessage());
        }

    }

    private static void shuffle() throws UnexpectedResponseException {
        pokerClient.shuffleDeck();
    }

    private static HandDTO dealHand(int numberOfCards) throws UnexpectedResponseException{
        return pokerClient.dealHand(numberOfCards);
    }

    private static String handAsString(HandDTO handDTO)  {
        return handDTO.getCards().stream().map(cardDTO ->
                constructHandAsString(cardDTO)).collect(Collectors.joining(" ,"));
    }

    private static String evaluateHand(HandDTO handDTO) throws UnexpectedResponseException {
        EvaluateResponse evaluateResponse = pokerClient.evaluateHand(handDTO);
        return evaluateResponse.getHandValue();
    }

    private static String constructHandAsString(CardDTO cardDTO) {
        return cardDTO.getRank() + "-" + cardDTO.getSuit();
    }
}

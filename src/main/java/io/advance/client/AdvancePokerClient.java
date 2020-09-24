package io.advance.client;

import io.advance.poker.simulation.entity.EvaluateResponse;
import io.advance.poker.simulation.entity.HandDTO;

public interface AdvancePokerClient {
    void shuffleDeck() throws UnexpectedResponseException;
    HandDTO dealHand(int numberOfCards) throws UnexpectedResponseException;
    EvaluateResponse evaluateHand(HandDTO cards) throws UnexpectedResponseException;
}

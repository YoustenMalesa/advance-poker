package io.advance.poker.simulation.api;

import io.advance.poker.simulation.entity.EvaluateResponse;
import io.advance.poker.simulation.entity.HandDTO;

public interface PokerAlgorithmProvider {
    void shuffleDeck();

    HandDTO dealHand(int numberOfCards);

    EvaluateResponse evaluateHand(HandDTO hand);
}

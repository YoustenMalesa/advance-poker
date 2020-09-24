package io.advance.poker.simulation.impl;

import io.advance.poker.simulation.*;
import io.advance.poker.simulation.api.PokerSimulationService;
import io.advance.poker.simulation.api.PokerAlgorithmProvider;
import io.advance.poker.simulation.entity.EvaluateResponse;
import io.advance.poker.simulation.entity.HandDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

@EJB
public class PokerSimulationServiceImpl implements PokerSimulationService {
    private PokerAlgorithmProvider pokerAlgorithmProvider;
    @Inject
    private PokerAlgorithmRegister algorithmRegister;
    @Inject
    @ConfigProperty(name = "algorithm")
    private String algorithm;

    @PostConstruct
    public void init() {
        pokerAlgorithmProvider = algorithmRegister.getAlgorithmProvider(algorithm);
    }

    @Override
    public void shuffleDeck() {
        pokerAlgorithmProvider.shuffleDeck();
    }

    @Override
    public HandDTO dealHand(int numberOfCards) {
        return pokerAlgorithmProvider.dealHand(numberOfCards);

    }

    @Override
    public EvaluateResponse evaluateHand(HandDTO hand) {
        return pokerAlgorithmProvider.evaluateHand(hand);
    }

}

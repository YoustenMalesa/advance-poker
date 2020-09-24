package io.advance.poker.simulation;

import io.advance.poker.simulation.api.PokerAlgorithmProvider;
import io.advance.poker.simulation.api.PolkerAlgorithmVariants;
import io.advance.poker.simulation.impl.FiveCardDrawAlgorithmProvider;
import javax.inject.Singleton;

@Singleton
public class PokerAlgorithmRegister {

    public PokerAlgorithmProvider getAlgorithmProvider(String algorithm)  {
        PokerAlgorithmProvider pokerAlgorithmProvider =  new FiveCardDrawAlgorithmProvider(); //default algorithm
        if(algorithm == null || algorithm.isEmpty()) {
            //return default algorithm
            return pokerAlgorithmProvider;
        }

        //Use properties file to change algorithm provider.
        if(algorithm.equalsIgnoreCase(PolkerAlgorithmVariants.FIVE_CARD_DRAW.getLabel())) {
            return pokerAlgorithmProvider;
        }

        return pokerAlgorithmProvider;
    }
}

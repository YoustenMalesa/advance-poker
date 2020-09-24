package io.advance.poker.simulation.api;

public enum PolkerAlgorithmVariants {
    FIVE_CARD_DRAW("Five-Card-Draw");

    private String label;
    PolkerAlgorithmVariants(String label) {
        this.label = label;
    }
    public String getLabel() {
        return this.label;
    }
}

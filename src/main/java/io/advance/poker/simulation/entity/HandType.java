package io.advance.poker.simulation.entity;

public enum HandType {
    STRAIGHT_FLUSH (1),
    FOUR_OF_A_KIND(2),
    FULL_HOUSE(3),
    FLUSH(4),
    STRAIGHT(5),
    THREE_OF_A_KIND(6),
    TWO_PAIR(7),
    ONE_PAIR(8),
    HIGH_CARD(9);

    private int value;
    private String type;
    HandType(int value) {this.value = value;}
    public int getValue() {
        return this.value;
    }
}

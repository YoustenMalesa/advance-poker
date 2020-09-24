package io.advance.poker.simulation.entity;

import io.advance.poker.simulation.Tables;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

/** Utility methods for evaluating or creating a hand of cards. */
@Stateless
public class Hand {

    public HandType rankHand(int handValue) {
        if (handValue > 6185) return(HandType.FOUR_OF_A_KIND);        // 1277 high card
        if (handValue > 3325) return(HandType.ONE_PAIR);         // 2860 one pair
        if (handValue > 2467) return(HandType.TWO_PAIR);         //  858 two pair
        if (handValue > 1609) return(HandType.THREE_OF_A_KIND);  //  858 three-kind
        if (handValue > 1599) return(HandType.STRAIGHT);         //   10 straights
        if (handValue > 322)  return(HandType.FLUSH);            // 1277 flushes
        if (handValue > 166)  return(HandType.FULL_HOUSE);       //  156 full house
        if (handValue > 10)   return(HandType.FOUR_OF_A_KIND);   //  156 four-kind
        return(HandType.STRAIGHT_FLUSH);                   //   10 straight-flushes
    }

    /**
     * Evaluates the given hand and returns its value as an integer.
     * Based on Kevin Suffecool's 5-card hand evaluator and with Paul Senzee's pre-computed hash.
     * @param cards a hand of cards to evaluate
     * @return the value of the hand as an integer between 1 and 7462
     */
    public int evaluate(List<Card> cards) {
        // Only 5-card hands are supported
        if (cards == null || cards.size() != 5) {
            throw new IllegalArgumentException("Only 5 cards are required.");
        }

        // Binary representations of each card
        final int c1 = cards.get(0).getValue();
        final int c2 = cards.get(1).getValue();
        final int c3 = cards.get(2).getValue();
        final int c4 = cards.get(3).getValue();
        final int c5 = cards.get(4).getValue();

        // No duplicate cards allowed
        if (hasDuplicates(new int[]{c1, c2, c3, c4, c5})) {
            throw new IllegalArgumentException("Hand has duplicates.");
        }

        // Calculate index in the flushes/unique table
        final int index = (c1 | c2 | c3 | c4 | c5) >> 16;

        // Flushes, including straight flushes
        if ((c1 & c2 & c3 & c4 & c5 & 0xF000) != 0) {
            return Tables.Flushes.TABLE[index];
        }

        // Straight and high card hands
        final int value = Tables.Unique.TABLE[index];
        if (value != 0) {
            return value;
        }

        // Remaining cards
        final int product = (c1 & 0xFF) * (c2 & 0xFF) * (c3 & 0xFF) * (c4 & 0xFF) * (c5 & 0xFF);
        return Tables.Hash.Values.TABLE[hash(product)];
    }

    /**
     * Creates a new 5-card hand from the given string.
     * @param string the string to create the hand from, such as "Kd 5s Jc Ah Qc"
     * @return a new hand as an array of cards
     * @see Card
     */
    public Card[] fromString(String string) {
        final String[] parts = string.split(" ");
        final Card[] cards = new Card[parts.length];

        if (parts.length != 5)
            throw new IllegalArgumentException("Exactly 5 cards are required.");

        int index = 0;
        for (String part : parts)
            cards[index++] = Card.fromString(part);

        return cards;
    }

    /**
     * Converts the given hand into concatenation of their string representations
     * @param cards a hand of cards
     * @return a concatenation of the string representations of the given cards
     */
    public String toString(Card[] cards) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cards.length; i++) {
            builder.append(cards[i]);
            if (i < cards.length - 1)
                builder.append(" ");
        }

        return builder.toString();
    }

    /**
     * Checks if the given array of values has any duplicates.
     * @param values the values to check
     * @return true if the values contain duplicates, false otherwise
     */
    private boolean hasDuplicates(int[] values) {
        Arrays.sort(values);
        for (int i = 1; i < values.length; i++) {
            if (values[i] == values[i - 1])
                return true;
        }
        return false;
    }

    private int hash(int key) {
        key += 0xE91AAA35;
        key ^= key >>> 16;
        key += key << 8;
        key ^= key >>> 4;
        return ((key + (key << 2)) >>> 19) ^ Tables.Hash.Adjust.TABLE[(key >>> 8) & 0x1FF];
    }
}

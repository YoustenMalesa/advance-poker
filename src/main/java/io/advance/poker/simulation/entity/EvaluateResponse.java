package io.advance.poker.simulation.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EvaluateResponse {
    @XmlElement(name = "hand_ranking")
    private int handRanking;
    @XmlElement(name = "hand_value")
    private String handValue;

    public int getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(int handRanking) {
        this.handRanking = handRanking;
    }

    public String getHandValue() {
        return handValue;
    }

    public void setHandValue(String hand) {
        this.handValue = hand;
    }
}

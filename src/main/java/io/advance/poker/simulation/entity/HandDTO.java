package io.advance.poker.simulation.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "hand")
public class HandDTO {
    @XmlElement(name = "cards")
    private List<CardDTO> cards;

    public HandDTO() {
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }
}

package io.advance;

import io.advance.poker.simulation.entity.*;
import io.advance.poker.simulation.impl.FiveCardDrawAlgorithmProvider;
import io.advance.poker.simulation.util.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class FiveCardDrawAlgorithmProviderTest {
  @Mock
  private Hand hand;
  @Mock
  private Deck deck;
  @Mock
  private ObjectMapper objectMapper;
  @InjectMocks
  private FiveCardDrawAlgorithmProvider fiveCardDrawAlgorithmProvider;

  @Test
  public void testShuffleDeck() {
    //Given

    //When
    fiveCardDrawAlgorithmProvider.shuffleDeck();

    //ThenList<Card>
    verify(deck).shuffle();
  }

  @Test
  public void testDealHand() {
    //Given
    int numberOfCards = 5;

    //When
    when(deck.dealHand(numberOfCards)).thenReturn(getCards());
    HandDTO handDTO = fiveCardDrawAlgorithmProvider.dealHand(5);

    //Then
    verify(deck).shuffle();
    verify(deck).dealHand(numberOfCards);
    assertNotNull(handDTO);
    assertEquals(5, handDTO.getCards().size());
  }

  @Test
  public void testEvaluateHand() {
    //Given
    HandDTO handDTO = new HandDTO();
    handDTO.setCards(getCardDTOs());

    //When
    when(hand.rankHand(anyInt())).thenReturn(HandType.FULL_HOUSE);

    //Then
    EvaluateResponse evaluateResponse = fiveCardDrawAlgorithmProvider.evaluateHand(handDTO);

    assertNotNull(evaluateResponse);
    assertEquals(3,evaluateResponse.getHandRanking());
    assertEquals(HandType.FULL_HOUSE.toString(), evaluateResponse.getHandValue());
  }

  private List<Card> getCards() {
    List<Card> cards = new ArrayList<>();
    Card card1 = new Card(8, 0x8000);
    Card card2 = new Card(1, 0x4000);
    Card card3 = new Card(7, 0x2000);
    Card card4 = new Card(12, 0x2000);
    Card card5 = new Card(5, 0x8000);

    cards.add(card1);
    cards.add(card2);
    cards.add(card3);
    cards.add(card4);
    cards.add(card5);

    return cards;
  }

  private List<CardDTO> getCardDTOs() {
    List<CardDTO> cards = new ArrayList<>();

    for(int x = 0; x < 5; x++) {
      CardDTO card = mock(CardDTO.class);
      cards.add(card);
    }
    return cards;
  }

}
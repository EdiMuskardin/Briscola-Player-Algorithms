package players;

import game.Card;
import game.Deck;
import game.Suit;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public abstract class AbstractPlayer {
    // played first in a round
    public boolean playFirst;
    // current hand
    public List<Card> hand;
    // cards won
    public List<Card> cardsWon;
    // briscola(main suit)
    public Card briscola;
    // all cards played
    public List<Card> cardsPlayed;
    // reference deck
    private Deck referenceDeck;

    public AbstractPlayer(){
        this.cardsWon = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.cardsPlayed = new ArrayList<>();
        this.referenceDeck = new Deck();
    }

    public void receiveCards(List<Card> cards){
        hand.addAll(cards);
    }

    public void collectRoundCards(List<Card> cards){
        cardsWon.addAll(cards);
    }

    public void printHand() {
        hand.forEach(System.out::print);
        System.out.print("\n");
    }

    public int getCurrentPoints(){
        int sum = 0;
        for (Card card : cardsWon)
            sum += card.getValue();
        return sum;
    }

    public int getWeakestCardIndex(Suit currBriscola){
        int minIndex = 0;
        for (int i = 1; i < this.hand.size(); i++) {
            if(this.hand.get(minIndex).isStrongerThen(this.hand.get(i), currBriscola))
                minIndex = i;
        }
        return minIndex;
    }

    public abstract Card makeMove(List<Card> cardsOnTable);

    public double opponentHasStrongerProbability(Card card, Suit currentBriscola){
        int isStrongerCounter = 0;
        double referenceDeckSize = referenceDeck.getDeck().size();
        for (Card remainingCard: referenceDeck.getDeck()){
            if (remainingCard.getSuit() == this.briscola.getSuit() && remainingCard.isStrongerThen(card, this.briscola.getSuit()))
                isStrongerCounter++;
            if (remainingCard.getSuit() != currentBriscola && remainingCard.isStrongerThen(card, currentBriscola))
                isStrongerCounter++;
        }
        return isStrongerCounter / referenceDeckSize;
    }


}

package game;

import players.AbstractPlayer;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EdiPlayer extends AbstractPlayer {
    int turnCounter = 0;
    public EdiPlayer(){
        removeFromReferenceDeck(Collections.singletonList(briscola));
    }

    @Override
    public Card makeMove(List<Card> cardsOnTable) {
        turnCounter++;
        if(turnCounter/2 >= 9)
            getReferenceDeck().getDeck().add(briscola);
        removeFromReferenceDeck(cardsOnTable);
        removeFromReferenceDeck(hand);

        double strongest = opponentHasStrongerProbability(hand.get(0), this.briscola.getSuit());
        double weakest =  strongest;
        int strongestIndex = 0;
        int weakestIndex = 0;

        for (int i = 1; i < hand.size(); i++) {
            double p = opponentHasStrongerProbability(hand.get(0), this.briscola.getSuit());
            if(strongest >= p) {
                strongest = p;
                strongestIndex = i;
            }
            if(weakest < p) {
                weakest = p;
                weakestIndex = i;
            }
        }
        return hand.remove(strongestIndex);
    }

    private void removeFromReferenceDeck(List<Card> playedCards){
        for (Iterator<Card> iter = getReferenceDeck().getDeck().listIterator(); iter.hasNext(); ) {
            Card a = iter.next();
            for (Card c: playedCards) {
                if (a.equals(c))
                    iter.remove();
            }
        }
    }
}

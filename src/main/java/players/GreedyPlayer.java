package players;

import game.Card;

import java.util.List;

public class GreedyPlayer extends AbstractPlayer {

    @Override
    public Card makeMove(List<Card> cardsOnTable) {
        if(this.playFirst){
            if(cardsOnTable.isEmpty()) {
                // return min points or briscula
                for (int i = 1; i < this.hand.size(); i++) {
                    if(this.hand.get(i).getSuit() == briscola.getSuit())
                        return this.hand.remove(i);
                }

                return this.hand.remove(getWeakestCardIndex(this.briscola.getSuit()));
            }
            else{
                Card opponentCard = cardsOnTable.get(1);
                if(opponentCard.isStrongerThen(cardsOnTable.get(0), this.briscola.getSuit())){
                    for (int i = 0; i < this.hand.size(); i++) {
                        if(hand.get(i).isStrongerThen(opponentCard, this.briscola.getSuit()))
                            return hand.remove(i);
                    }
                }
                // return min points
                return this.hand.remove(getWeakestCardIndex(this.briscola.getSuit()));
            }
        }else {
            if(this.hand.size() == 4){
                Card opponentCard = cardsOnTable.get(0);
                for (int i = 0; i < this.hand.size(); i++) {
                    if(hand.get(i).isStrongerThen(opponentCard, this.briscola.getSuit()))
                        return hand.remove(i);
                }
                // return min points
                int minIndex = 0;
                for (int i = 1; i < this.hand.size(); i++) {
                    if(!this.hand.get(i).isStrongerThen(this.hand.get(minIndex), briscola.getSuit()))
                        minIndex = i;
                }
                return hand.remove(minIndex);
            }else {
                Card opponentCard = cardsOnTable.get(0);
                Card opponentCard2 = cardsOnTable.get(0);
                for (int i = 0; i < this.hand.size(); i++) {
                    if(hand.get(i).isStrongerThen(opponentCard, this.briscola.getSuit()) &&
                            hand.get(i).isStrongerThen(opponentCard2, this.briscola.getSuit()))
                        return hand.remove(i);
                }
                // return min points
                return this.hand.remove(getWeakestCardIndex(this.briscola.getSuit()));
            }
        }
    }
}

package players;

import game.Card;
import players.AbstractPlayer;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer {
    private Random random = new Random();

    @Override
    public Card makeMove(List<Card> cardsOnTable) {
        return this.hand.remove(random.nextInt(this.hand.size()));
    }
}

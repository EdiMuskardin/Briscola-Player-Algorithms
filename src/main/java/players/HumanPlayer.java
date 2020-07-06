package players;

import game.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class HumanPlayer extends AbstractPlayer {
    private Scanner in = new Scanner(System.in);

    // Indexes start from 1 to 4, from left to right
    public Card makeMove(List<Card> cardsOnTable) {
        this.printHand();
        return hand.remove(in.nextInt() - 1);
    }
}

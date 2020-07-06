import game.Card;
import game.GameSession;
import game.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestLogic {

    @Test
    public void TestWinTurn(){
        List<Card> cards = Arrays.asList(
            new Card(Suit.Bastoni, 1),
            new Card(Suit.Bastoni, 3),
            new Card(Suit.Bastoni, 2),
            new Card(Suit.Bastoni, 13)
        );

        cards.get(0).setValue(11);
        cards.get(1).setValue(10);
        cards.get(2).setValue(0);
        cards.get(3).setValue(4);

        GameSession game = new GameSession();
        // p1 won
        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Coppe));
        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Bastoni));

        cards = Arrays.asList(
                new Card(Suit.Bastoni, 1),
                new Card(Suit.Bastoni, 3),
                new Card(Suit.Bastoni, 13),
                new Card(Suit.Dinari, 2)
        );

        cards.get(0).setValue(11);
        cards.get(1).setValue(10);
        cards.get(2).setValue(4);
        cards.get(3).setValue(0);


        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Coppe));
        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Dinari));

        cards = Arrays.asList(
                new Card(Suit.Coppe, 6),
                new Card(Suit.Spade, 3),
                new Card(Suit.Bastoni, 2),
                new Card(Suit.Dinari, 11)
        );

        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Coppe));
        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Bastoni));
        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Spade));
        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Dinari));

        cards = Arrays.asList(
                new Card(Suit.Bastoni, 13),
                new Card(Suit.Bastoni, 3),
                new Card(Suit.Bastoni, 1),
                new Card(Suit.Bastoni, 2)
        );

        cards.get(0).setValue(4);
        cards.get(1).setValue(10);
        cards.get(2).setValue(11);
        cards.get(3).setValue(0);

        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Coppe));
        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Bastoni));

        cards = Arrays.asList(
                new Card(Suit.Dinari, 13),
                new Card(Suit.Bastoni, 2),
                new Card(Suit.Bastoni, 3),
                new Card(Suit.Dinari, 1)
        );

        cards.get(0).setValue(4);
        cards.get(1).setValue(0);
        cards.get(2).setValue(10);
        cards.get(3).setValue(11);


        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Bastoni));
        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Dinari));

        cards = Arrays.asList(
                new Card(Suit.Coppe, 13),
                new Card(Suit.Coppe, 12),
                new Card(Suit.Coppe, 3),
                new Card(Suit.Coppe, 1)
        );

        cards.get(0).setValue(4);
        cards.get(1).setValue(3);
        cards.get(2).setValue(10);
        cards.get(3).setValue(11);


        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Bastoni));
        Assert.assertFalse(game.didFirstPlayerWinRound(cards, Suit.Coppe));

        cards = Arrays.asList(
                new Card(Suit.Dinari, 2),
                new Card(Suit.Dinari, 4),
                new Card(Suit.Dinari, 6),
                new Card(Suit.Coppe, 12)
        );

        cards.get(0).setValue(0);
        cards.get(1).setValue(0);
        cards.get(2).setValue(0);
        cards.get(3).setValue(4);

        Assert.assertTrue(game.didFirstPlayerWinRound(cards, Suit.Dinari));

    }
}

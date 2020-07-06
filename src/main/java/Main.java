import game.EdiPlayer;
import game.GameSession;
import players.GreedyPlayer;
import players.RandomPlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameSession game = new GameSession();
        int firstWon = 0, secondWon = 0, draw = 0;
        List<Integer> firstRes = new ArrayList<>(), secondRes = new ArrayList<>();
        int numGames = 100000;
        for (int i = 0; i < numGames; i++) {
            game.startGame(new RandomPlayer(), new EdiPlayer());
            firstRes.add(game.player1.getCurrentPoints());
            secondRes.add(game.player2.getCurrentPoints());
        }

        for (int i = 0; i < numGames; i++) {
            if(firstRes.get(i) == 60)
                draw++;
            else if(firstRes.get(i) > 60)
                firstWon++;
            else
                secondWon++;
        }

        double firstAverage = firstRes.stream().mapToInt(val -> val).average().orElse(0.0);
        double secondAverage = secondRes.stream().mapToInt(val -> val).average().orElse(0.0);

        System.out.println("First won " + (firstWon / (double) numGames) * 100 + "%");
        System.out.println("Second won " + secondWon / (double) numGames * 100 + "%");
        System.out.println("Draw " + draw / (double) numGames * 100 + "%");

        System.out.println("Average points first: " + firstAverage);
        System.out.println("Average points second: " + secondAverage);

    }
}

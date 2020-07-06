package game;

import lombok.Getter;
import players.AbstractPlayer;
import players.GreedyPlayer;
import players.RandomPlayer;

import java.util.*;

@Getter
public class GameSession {
    public AbstractPlayer player1;
    public AbstractPlayer player2;

    private List<Card> cardsOnTable;
    private Deck deck;
    public Card briscola;


    public void startGame(){
        startGame(new RandomPlayer(), new RandomPlayer());
    }

    public void startGame(AbstractPlayer playerOne, AbstractPlayer playerTwo){
        deck = new Deck();
        cardsOnTable = new ArrayList<>();

        deck.shuffle();

        this.player1 = playerOne;
        this.player2 = playerTwo;

        // initial handout of cards and briscola
        takeCardFromDeck(player1, 2);
        takeCardFromDeck(player2, 2);
        briscola = deck.remove(0);
        deck.add(briscola);
        takeCardFromDeck(player1, 2);
        takeCardFromDeck(player2, 2);

        // set briscula to both players
        player1.setBriscola(briscola);
        player2.setBriscola(briscola);

        // player1 starts first
        player1.setPlayFirst(true);

        // as at the begging player 1 starts first
        AbstractPlayer roundWinner = player1;
        AbstractPlayer roundLoser = player2;

        // while there are cards in game(hand)
        while (!player1.hand.isEmpty()) {
            //System.out.println(player1.isPlayFirst() ? "Player1 plays first." : "Player 2 plays first.");
            cardsOnTable.clear();
            for (int i = 0; i < 2; i++) {
                updateTable(roundWinner.makeMove(cardsOnTable));
                updateTable(roundLoser.makeMove(cardsOnTable));
            }
            // check it the first player in this turn( the one who threw first card) has won
            boolean firstPlayerInTurnWon = didFirstPlayerWinRound(this.cardsOnTable, this.briscola.getSuit());
            // check if that is player one or player 2
            // who won collects the cards on the table
            boolean player1WonTurn = (firstPlayerInTurnWon && player1.isPlayFirst()) || (!firstPlayerInTurnWon && !player1.isPlayFirst());
            // set run winners and losers
            roundWinner = player1WonTurn ? player1 : player2;
            roundLoser = !player1WonTurn ? player1 : player2;

            // winner collects the cards
            roundWinner.collectRoundCards(cardsOnTable);
            // winner plays first in the next round
            roundWinner.setPlayFirst(true);
            roundLoser.setPlayFirst(false);

            // if last round in next, do not take any cards
            // players still have to play last cards they have in hand
            if (deck.isEmpty())
                continue;

            //System.out.println(player1First ? "Player1 goes first." : "Player2 goes first.");
            // winner of the round takes the first card and then in alternating order each gets 2
            for (int i = 0; i < 2; i++) {
                takeCardFromDeck(roundWinner, 1);
                takeCardFromDeck(roundLoser, 1);
            }
        }

    }

    // true if player first player OF THAT TURN won, false if second player won
    // note that first and third card(inices 0 and 2) are from first player of the round
    public boolean didFirstPlayerWinRound(List<Card> currentTable, Suit briscola){
        boolean brisculaInGame = false;
        int strongest = 0;
        for (Card card : currentTable) {
            if (card.isSuit(briscola))
                brisculaInGame = true;
        }
        Suit currSuit = brisculaInGame ? briscola : currentTable.get(0).getSuit();
        //System.out.println("Current briscula " + currSuit);
        //System.out.println(this.cardsOnTable);
        for (int i = 1; i < currentTable.size(); i++) {
            if(currentTable.get(i).isStrongerThen(currentTable.get(strongest), currSuit))
                strongest = i;
        }

        //System.out.println(currentTable.get(strongest));
        return (strongest == 0 || strongest == 2);
    }

    // player p takes n cards from deck and puts them in hand
    private void takeCardFromDeck(AbstractPlayer p, int n){
        List<Card> pasca = new ArrayList<>();
        for (int i = 0; i < n; i++)
            pasca.add(deck.remove(0));
        p.receiveCards(pasca);
    }

    // put the card on table and update player knowledge about passed cards
    private void updateTable(Card card){
        cardsOnTable.add(card);
        player1.getCardsPlayed().add(card);
        player2.getCardsPlayed().add(card);
    }
}

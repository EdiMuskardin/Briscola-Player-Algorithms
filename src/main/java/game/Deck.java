package game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class Deck {
    private List<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for (int i = 2; i < 8; i++){
                if(i == 3)
                    deck.add(new Card(suit, i, 10)); // tre
                else
                    deck.add(new Card(suit, i, 0)); // liso
            }
            deck.add(new Card(suit, 1, 11)); // AS
            deck.add(new Card(suit, 13, 4)); // RE
            deck.add(new Card(suit, 12, 3)); // CAVAL
            deck.add(new Card(suit, 11, 2)); // FANAT
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    public Card remove(int n){
        return deck.remove(n);
    }

    public void add(Card c){
        deck.add(c);
    }

}

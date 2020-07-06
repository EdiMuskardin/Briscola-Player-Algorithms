package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private Suit suit;
    private int number;
    private int value;

    public boolean isStrongerThen(Card otherCard, Suit briscola){
        if(otherCard.suit == briscola && this.suit != briscola)
            return false;
        if(otherCard.suit != briscola && this.suit == briscola)
            return true;
        if(this.value == 0 && otherCard.value == 0)
            return otherCard.number < this.number;

        return otherCard.value < this.value;
    }

    public boolean isSuit(Suit suit){
        return this.suit == suit;
    }

    @Override
    public String toString() {
        if(number == 1)
            return suit.toString() + "_" + "AS";
        if(number == 13)
            return suit.toString() + "_" + "RE";
        if(number == 12)
            return suit.toString() + "_" + "CA";
        if(number == 11)
            return suit.toString() + "_" + "FA";
        return suit.toString() + "_" + number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }
        Card c1 = (Card) obj;
        return this.getSuit() == c1.getSuit() && this.getNumber() == c1.getNumber();
    }
}

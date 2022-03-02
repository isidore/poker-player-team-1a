package org.leanpoker.player;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {

        this.rank = rank;
        this.suit = suit;

    }

    @Override
    public String toString() {
        return  rank + " of " + suit;
    }
}

package org.leanpoker.player;

public class Card {
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

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

    public int getNumericRank() {
        char r = getRank().charAt(0);
        switch (r) {
            case 'A': return 14;
            case 'K': return 13;
            case 'Q': return 12;
            case 'J': return 11;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case '1': return 10;
        }
        return 0;
    }
}

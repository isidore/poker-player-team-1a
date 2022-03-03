package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.lambda.query.Queryable;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        var poker = new Poker(request);
        if (poker.isFirstBet()){
            if (isPoorHand(poker)){
                return 0;
            }

        }
        if (isGreatHand(poker)) {
            return poker.getAllIn();
        }
        return poker.getMinimumRaise();
    }

    private static boolean isPoorHand(Poker poker) {
        // if no pair or no face card

        Card[] playersCards = poker.getPlayersCards();
        return isPoorHand(playersCards);
    }

    public static boolean isPoorHand(Card[] playersCards) {
        var pair = isPair(playersCards);
        var isHighCards = isHighCards(playersCards);
        return !(isHighCards || pair);
    }

    private static boolean isHighCards(Card[] playersCards) {
        return Queryable.as(playersCards).any(c -> c.getNumericRank() > 11);
    }

    private static boolean isPair(Card[] playersCards) {
        return playersCards[0].getRank().equals(playersCards[1].getRank());
    }

    private static boolean isGreatHand(Poker poker) {
        return poker.isThreeOfAKind();
    }


    public static void showdown(JsonElement game) {
    }
}

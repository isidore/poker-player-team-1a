package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.spun.util.logger.SimpleLogger;
import org.lambda.query.Queryable;

public class Player {

    static final String VERSION = "v.0.0.2-pair";

    public static int betRequest(JsonElement request) {
        SimpleLogger.variable(request.toString());
        var poker = new Poker(request);
        if (poker.isFirstBet()){
            if (isPoorHand(poker)){
                return 0;
            }else{
                return poker.getAllIn();
            }

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
        return !(pair);
    }

    private static boolean isHighCards(Card[] playersCards) {
        return Queryable.as(playersCards).any(c -> c.getNumericRank() > 11);
    }

    private static boolean isPair(Card[] playersCards) {
        return playersCards[0].getRank().equals(playersCards[1].getRank());
    }

    private static boolean isGreatHand(Poker poker) {
        return poker.isThreeOfAKind() || poker.isStraight();
    }


    public static void showdown(JsonElement game) {
    }
}

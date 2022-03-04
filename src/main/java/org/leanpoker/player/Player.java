package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.spun.util.logger.SimpleLogger;
import org.lambda.query.Queryable;

public class Player {

    static final String VERSION = "v.0.0.7-double";

    public static int betRequest(JsonElement request) {
        SimpleLogger.variable(request.toString());
        var poker = new Poker(request);
        if (poker.isFirstBet()){
            double poorHand = isPoorHand(poker);
            return (int) (poorHand * poker.getAllIn());

        }

        return poker.getAllIn();
    }

    private static double isPoorHand(Poker poker) {
        // if no pair or no face card

        Card[] playersCards = poker.getPlayersCards();
        return isPoorHand(playersCards);
    }

    public static double isPoorHand(Card[] playersCards) {
        var pair = isPair(playersCards);
        var isHighCards = isHighCards(playersCards);
        var isPossibleFlush = isPossibleFlush(playersCards);
        var isPossibleStraight = isPossibleStraight(playersCards);
        return !(pair || isHighCards || isPossibleFlush || isPossibleStraight)? 0.0 : 1.0;
    }

    private static boolean isPossibleFlush(Card[] playersCards) {
        return playersCards[0].getSuit().equals(playersCards[1].getSuit());
    }
    private static boolean isPossibleStraight(Card[] playersCards) {
        Card[] cards = Queryable.as(playersCards).orderBy(c -> c.getNumericRank()).asArray();

        return cards[0].getNumericRank() == (cards[1].getNumericRank() -1);
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

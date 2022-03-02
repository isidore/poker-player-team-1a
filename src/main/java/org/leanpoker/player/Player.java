package org.leanpoker.player;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        var poker = new Poker(request);
        if (isGreatHand(poker)) {
            return poker.getAllIn();
        }
        return poker.getMinimumRaise();
    }

    private static boolean isGreatHand(Poker poker) {
        return poker.isThreeOfAKind();
    }


    public static void showdown(JsonElement game) {
    }
}

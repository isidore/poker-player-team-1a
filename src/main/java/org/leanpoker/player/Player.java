package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        var poker = new Poker(request);
        if (poker.isThreeOfAKind()) {
            return poker.getAllIn();
        }
        return poker.getMinimumRaise();
    }

    public static void showdown(JsonElement game) {
    }
}

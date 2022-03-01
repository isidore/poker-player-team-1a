package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Poker {
    private JsonObject jsonObject;

    public Poker(JsonElement jsonObject) {
        this.jsonObject = jsonObject.getAsJsonObject();
    }

    public int getCurrentBuyIn() {
        return  jsonObject.get("current_buy_in").getAsInt();
    }

    public int getMinimumRaise() {
        return  jsonObject.get("minimum_raise").getAsInt();
    }

    public Card[] getPlayersCards() {
    var player = getPlayer();
    }

    private PokerPlayer getPlayer() {
        return new PokerPlayer(jsonObject.get("players").getAsJsonArray().getjsonObject.get("in_action").getAsInt()])
    }
}

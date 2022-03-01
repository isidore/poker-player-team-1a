package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PokerPlayer {
    private JsonObject jsonObject;

    public PokerPlayer(JsonElement jsonElement) {

        this.jsonObject = jsonElement.getAsJsonObject();
    }

    public Card[] getCards() {

        String rank = jsonObject.get("hole_cards").getAsJsonArray().get(0)
                .getAsJsonObject().get("rank").getAsString();
        String suit = "";
        return new Card[]{new Card(rank,suit)};
    }
}

package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PokerPlayer {
    private JsonObject jsonObject;

    public PokerPlayer(JsonElement jsonElement) {

        this.jsonObject = jsonElement.getAsJsonObject();
    }

    public Card[] getCards() {

        JsonObject card1 = jsonObject.get("hole_cards").getAsJsonArray().get(0)
                .getAsJsonObject();
        JsonObject card2 = jsonObject.get("hole_cards").getAsJsonArray().get(1)
                .getAsJsonObject();
        String rank1 = card1.get("rank").getAsString();
        String suit1 = card1.get("suit").getAsString();
        String rank2 = card2.get("rank").getAsString();
        String suit2 = card2.get("suit").getAsString();
        return new Card[]{new Card(rank1,suit1),new Card(rank2,suit2)};
    }


    public int getStack() {
        return jsonObject.get("stack").getAsInt();
    }
}

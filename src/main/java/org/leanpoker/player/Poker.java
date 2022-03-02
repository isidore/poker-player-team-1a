package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Poker {
    private JsonObject jsonObject;

    public Poker(JsonElement jsonObject) {
        this.jsonObject = jsonObject.getAsJsonObject();
    }

    public int getCurrentBuyIn() {
        return jsonObject.get("current_buy_in").getAsInt();
    }

    public int getMinimumRaise() {
        return jsonObject.get("minimum_raise").getAsInt();
    }

    public Card[] getPlayersCards() {
        var player = getPlayer();
        return player.getCards();
    }

    private PokerPlayer getPlayer() {
        return new PokerPlayer(jsonObject.get("players").getAsJsonArray().get(jsonObject.get("in_action").getAsInt()));
    }

    public Card[] getCommunityCards() {
        var cardsJson = jsonObject.get("community_cards").getAsJsonArray();
        var cards = new ArrayList<Card>();
        for (int i = 0; i < cardsJson.size(); i++) {
            JsonObject card = cardsJson.get(i).getAsJsonObject();
            String rank = card.get("rank").getAsString();
            String suit = card.get("suit").getAsString();
            cards.add(new Card(rank, suit));
        }
        return cards.toArray(new Card[0]);
    }
}

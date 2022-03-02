package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.lambda.query.Query;
import org.lambda.query.Queryable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Poker {
    private JsonObject jsonObject;

    public Poker(JsonElement jsonObject) {
        this.jsonObject = jsonObject.getAsJsonObject();
    }

    public static boolean isStraight(Card[] cards) {
        Queryable<Card> cards1 = Queryable.as(cards).orderBy(c -> c.getNumericRank());
        int start = 0;
        for (int i = 0; i < cards1.size(); i++) {
            if (i == 0) {
                start = cards1.get(i).getNumericRank();
            }
            else{
                var current = cards1.get(i).getNumericRank();
                if (start +1  == current){
                    start = current;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
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

    public boolean isThreeOfAKind() {
        // put the two arrays together
        // take the first...
        var cards =Queryable.as(getPlayersCards());
        cards.addAll(Arrays.asList(getCommunityCards()));
        Queryable<Map.Entry<String, Queryable<Card>>> entries = Query.groupBy(cards, (Card c) -> c.getRank()).where(e -> 3 <= e.getValue().size() );

        return 0 < entries.size() ;
    }

    public int getAllIn() {
        return getPlayer().getStack();
    }
}

package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class End2EndTests {
    @Test
    void test1() {
        var json = """
              {"tournament_id":"621e76b469c6d30004af2f7d","game_id":"6222650da86dfe00044a8c96","round":1,"players":[{"name":"Even Better Funny FIsh","stack":1000,"status":"active","bet":0,"time_used":10062,"version":"Smart fish?","id":0},{"name":"purple liter","stack":998,"status":"active","bet":0,"time_used":6670,"version":"4.2","id":1},{"name":"StillNoWin","stack":994,"status":"active","bet":2,"time_used":6540,"version":"Default Java folding player","id":2},{"name":"sUpERUnSAFePoKeR","stack":0,"status":"out","bet":0,"time_used":14676,"version":"UNSAFe v2","id":3},{"name":"Better Folders","stack":2002,"status":"active","bet":4,"time_used":11321,"version":"Default Ruby folding player","id":4},{"name":"Team 1A","stack":1000,"status":"active","bet":0,"hole_cards":[{"rank":"K","suit":"clubs"},{"rank":"10","suit":"hearts"}],"time_used":8689,"version":"v.0.0.1-log","id":5}],"small_blind":2,"big_blind":4,"orbits":0,"dealer":1,"community_cards":[],"current_buy_in":4,"pot":6,"in_action":5,"minimum_raise":2,"bet_index":3}
                """;
        JsonElement jsonObject = new JsonParser().parse(json);
        int actual = Player.betRequest(jsonObject);
        Assertions.assertEquals(-1, actual);
    }
}

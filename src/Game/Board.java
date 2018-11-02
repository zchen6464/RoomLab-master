package Game;

import Rooms.Room;

public class Board {
    private Room game[][];

    Board(Room[][] game) {
        this.game = game;
    }

    public String display() {
        String x = "";
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {

                if(game[i][j].occupant != null)
                {
                    x += "[o]";
                }
                else
                {
                    if(game[i][j].str == null)
                    {
                        x += "[ ]";
                    }
                    else
                    {
                        x += "[█]";
                    }
                }
            }
            x = x + "\n";
        }
        return x;
    }
}
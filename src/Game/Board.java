package Game;

import Rooms.Room;

public class Board {
    private Room game[][];

    Board(Room[][] game) {
        this.game = game;
    }               //use building[][] to create a copy [][]

    public String display() {                               //use data to create map
        String x = "";
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {

                if(game[i][j].occupant != null)             // use different symbols depending on the designation string "str"
                {
                    x += "[O]";                             //if player(occupant) in room, map displays [o], overrides all other symbols
                }
                else
                {
                    if(game[i][j].str.equals("blocked"))    // if room cannot be entered(blocked)
                    {
                        x += "[X]";
                    }
                    else if(game[i][j].str.equals("nempty")) // if room is not empty(have event conditions)
                    {
                        x += "[█]";
                    }
                    else if(game[i][j].state.equals("v"))
                    {
                        x += "[ ]";
                    }
                    else if(game[i][j].str.equals("empty"))// if room is empty(no events)
                    {
                        x += "[░]";
                    }
                }
            }
            x = x + "\n";
        }
        return x;
    }
}


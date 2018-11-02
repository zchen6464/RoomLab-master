package Game;

import People.Person;
import Rooms.BossRoom;
import Rooms.Room;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {


    private static boolean gameOn = true;

    public static void main(String[] args)
    {
        System.out.println("Choose map size");
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter length:");
        int length = in.nextInt();
        System.out.println("Please enter width:");
        int width = in.nextInt();
        Room[][] building = new Room[length][width];
        //Fill the building with normal rooms
        for (int x = 0; x<building.length; x++)
        {
            for (int y = 0; y < building[x].length; y++)
            {
                building[x][y] = new Room(x,y);
            }
        }

        //Create a random winning room.
        /*int x1 = (int)(Math.random()*building.length);
        int y1 = (int)(Math.random()*building.length);
        building[x1][y1] = new WinningRoom(x1, y1);
*/

        int x2 = (int)(Math.random()*building.length);
        int y2 = (int)(Math.random()*building.length);
        building[x2][y2] = new BossRoom(x2, y2);

        //Setup player 1 and the input scanner
        Person player1 = new Person("FirstName", "FamilyName", 0,0);
        building[0][0].enterRoom(player1);
        String x = new Board(building).display();
        System.out.println(x);
        while(gameOn)
        {
            if(player1.fight != true) {
                System.out.println("Where would you like to move? (Choose N, S, E, W)");
                String move = in.nextLine();
                if (validMove(move, player1, building)) {
                    x = new Board(building).display();
                    System.out.println(x);
                    System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

                } else {
                    System.out.println("Please choose a valid move.");
                }
            }
        }
        in.close();
    }

    /**
     * Checks that the movement chosen is within the valid game map.
     * @param move the move chosen
     * @param p person moving
     * @param map the 2D array of rooms
     * @return
     */
    public static boolean validMove(String move, Person p, Room[][] map)
    {
        move = move.toLowerCase().trim();
        switch(move){
            case "n":
                if (p.getxLoc() > 0)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }
            case "e":
                if (p.getyLoc()< map[p.getyLoc()].length -1)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }

            case "s":
                if (p.getxLoc() < map.length - 1)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }

            case "w":
                if (p.getyLoc() > 0)
                {
                    map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                    map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
                    return true;
                }
                else
                {
                    return false;
                }
            default:
                break;

        }
        return true;
    }
    public static void gameOff()
    {
        gameOn = false;
    }



}
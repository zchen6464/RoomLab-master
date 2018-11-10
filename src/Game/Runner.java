package Game;

import People.Person;
import Rooms.BossRoom;
import Rooms.Obstacle;
import Rooms.Room;
import Rooms.WinningRoom;

import java.util.Scanner;

public class Runner {


    private static boolean gameOn = true;

    public static void main(String[] args)
    {
        System.out.println("Choose map size");
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter length of map");
        int length = in.nextInt();
        System.out.println("Map will be " + length + " room long\n");
        System.out.println("Please enter width of map:");
        int width = in.nextInt();
        System.out.println("Map will be " + width + " room wide\n");
        Room[][] building = new Room[length][width];

        //Fill the building with normal rooms
        for (int x = 0; x<building.length; x++)
        {
            for (int y = 0; y < building[x].length; y++)
            {
                building[x][y] = new Room(x,y);
            }
        }
        //Setup player 1 and the input scanner
        in = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String FirstName = in.nextLine();
        System.out.println("Please enter your last name");
        String FamilyName = in.nextLine();
        //game start screen
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String greetings = "Greetings, ";
        if(FirstName.length() > 1)
        {
            greetings += FirstName.substring(0,1).toUpperCase() + FirstName.substring(1);
        }
        else
        {
            greetings += FirstName.toUpperCase();
        }
        if(FamilyName.length() > 1)
        {
            greetings += FamilyName.substring(0,1).toUpperCase() + FamilyName.substring(1);
        }
        else
        {
            greetings += FamilyName.toUpperCase();
        }
        System.out.println(greetings + "\nGame Start");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        int x1 = (int)(Math.random()*building.length);
        int y1 = (int)(Math.random()*building[x1].length);
        Person player1 = new Person(FirstName, FamilyName,x1,y1);
        building[x1][y1].enterRoom(player1);
        System.out.println("You wake up in an unfamiliar room. You find a loaf of wheat bread. Your objective is to escape this place.");
        //Create a random event rooms (winningRoom, bossRoom).
        int x2 = (int)(Math.random()*building.length);
        int y2 = (int)(Math.random()*building[x2].length);
        while((x2 == x1 && y2 == y1))
        {
            x2 = (int)(Math.random()*building.length);
            y2 = (int)(Math.random()*building[x2].length);
        }
        building[x2][y2] = new WinningRoom(x2, y2);
        int x3 = (int)(Math.random()*building.length);
        int y3 = (int)(Math.random()*building[x3].length);
        while((x3 == x2 && y3 == y2) || (x3 == x1 && y3 == y1))
        {
            x3 = (int)(Math.random()*building.length);
            y3 = (int)(Math.random()*building[x3].length);
        }
        building[x3][y3] = new BossRoom(x3, y3);
        if(length * width >= 40) //if area >= 40 units, obstacles are created
        {
            for(int i = 0; i <  building.length ; i = i + (int)((Math.random() * 2)+ 1))
            {
                for(int j = 0; j <  building.length ; j = j + (int)((Math.random() * 2)+ 2))
                {
                    if(!((i == x3 && j == y3) || (i == x2 && j == y2) || (i == x1 && j == y1))) //cannot create obstacles if player is in room || if room is not empty
                    {
                        building[i][j] = new Obstacle(i, j);
                    }
                }
            }
        }
        System.out.println(new Board(building).display());
        while(gameOn)
        {
            if(player1.fight != true) {
                System.out.println("Where would you like to move? (Choose N, S, E, W)");
                String move = in.nextLine();
                if (validMove(move, player1, building)) {
                    System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
                } else {
                    System.out.println("Please choose a valid move.");
                }
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(new Board(building).display());
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
                    if( map[p.getxLoc()-1][p.getyLoc()].str.equals("blocked"))
                    {
                        return false;
                    }
                    else
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            case "e":
                if (p.getyLoc()< map[p.getyLoc()].length -1)
                {
                    if( map[p.getxLoc()][p.getyLoc() + 1].str.equals("blocked"))
                    {
                        return false;
                    }
                    else
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
                        return true;
                    }
                }
                else
                {
                    return false;
                }

            case "s":
                if (p.getxLoc() < map.length - 1)
                {
                    if( map[p.getxLoc()+1][p.getyLoc()].str.equals("blocked"))
                    {
                        return false;
                    }
                    else
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc() + 1][p.getyLoc()].enterRoom(p);
                        return true;
                    }
                }
                else
                {
                    return false;
                }

            case "w":
                if (p.getyLoc() > 0)
                {
                    if( map[p.getxLoc()][p.getyLoc()-1].str.equals("blocked"))
                    {
                        return false;
                    }
                    else
                        {
                            map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                            map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
                            return true;
                        }
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
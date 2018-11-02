package Rooms;

import Game.Runner;
import People.Person;

import java.util.Scanner;

public class BossRoom extends Room{
private String[] Boss = {"Troll", "Dragon"};
    public BossRoom (int x, int y) {
        super(x, y);
        str = " ";
    }

    /**
     * Triggers the game ending conditions.
     * @param x the Person entering
     */
    public void enterRoom(Person x) {
        occupant = x;
        occupant.fight = true;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("You found the boss room.");
        fight();
    }

    public void fight() {
        int hp = (int) (Math.random() * 1000) + 100;
        int turn = 0;
        int x = (int) (Math.random());
        System.out.println("A wild " + Boss[x] + " appeared!\n");
        System.out.println("Options:");
        System.out.println("Press 1 to fight");
        System.out.println("Press 2 to flee");
        Scanner in = new Scanner(System.in);
            if (in.nextInt() == 1) {
                System.out.println("You choose to fight the " + Boss[x] + "\n");
                while (hp >= 1) {
                    if (occupant.php > 0) {
                        System.out.println("Your health: " + occupant.php);
                        System.out.println("Boss health: " + hp + "\n");
                        System.out.println("Options:");
                        System.out.println("Press 1 to kick");
                        System.out.println("Press 2 to punch");
                        System.out.println("Press 3 to flee");
                        if (in.nextInt() != 3) {
                            int dmg = (int) (Math.random() * 10 + 1);
                            hp = hp - dmg;
                            int npcdmg = (int) (Math.random() * 10 + 10);
                            occupant.php -= npcdmg;
                            System.out.println("You dealt " + dmg + " damage to the " + Boss[x]);
                            System.out.println("The boss dealt " + npcdmg + " damage to you\n");
                        } else {
                            System.out.println("You choose to flee");
                            this.occupant.fight = false;
                            return;
                        }
                    } else {
                        System.out.println("You died");
                        Runner.gameOff();
                        return;
                    }
                }
            } else {
                System.out.println("You choose to flee");
                this.occupant.fight = false;
            }
        }
}



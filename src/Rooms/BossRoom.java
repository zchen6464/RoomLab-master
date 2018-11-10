package Rooms;

import Game.Runner;
import People.Consumable;
import People.Items;
import People.Person;

import java.util.Scanner;

public class BossRoom extends Room {
    private String[] Boss = {"Troll", "Dragon", "Zombie", "Aliens"}; // boss option
    boolean eat = false;                         //  consuming state(no action, can use items)
    private int tempnum = (int) (Math.random() * 4); //temp number used to choose boss, useless

    public BossRoom(int x, int y) {
        super(x, y);
        str = "nempty"; //not empty room
    }

    public void enterRoom(Person x) {
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        System.out.println("A wild " + Boss[tempnum] + " appeared!\n");
        System.out.println("Options:");
        System.out.println("Press 1 to fight");
        System.out.println("Press 2 to flee");
        Scanner in = new Scanner(System.in);
        if (in.nextInt() == 1)     //choose to fight or flee
        {                          // if fight:enter fight state with more options available
            System.out.println("You choose to fight the " + Boss[tempnum] + "\n");
            occupant.fight = true;
            fight();
        }
        else if(in.nextInt() == 2)  // if flee exits fight state exits this room(class)
        {
            System.out.println("You choose to flee");
            occupant.fight = false;
            return;
        }
    }

    public void fight() {
        int hp = (int) (Math.random() * 1000) + 100;
        int php = getHp(occupant);
        Scanner input = new Scanner(System.in);
        while (php > 0) //if player health <= 0 , exits no matter the condition
        {
            if(hp <= 0) //second exit condition, boss hp <= 0
            {
                System.out.println("You defeated the boss");
                occupant.php = php;
                occupant.fight = false;
                return;
            }
            else {
                while (eat) { //"eat" state options
                    if (tempList.toArray().length == 0) { //exits "eat" state if item list (tempList) = 0/null
                        eat = false;
                    } else {
                        String in;
                        in = input.nextLine();
                        use(in);
                    }
                }
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("Your health: " + php);
                System.out.println("Boss health: " + hp + "\n");
                System.out.println("Options:");
                System.out.println("Press 1 to attack");
                System.out.println("Press 2 to use items");
                System.out.println("Press 3 to flee");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                int inNum = input.nextInt();
                if (inNum == 3) { //flees fight after entering "fight" state
                    System.out.println("You choose to flee");
                    this.occupant.fight = false;
                    return;
                } else if (inNum == 2) { //enters "eat" state, only able to use items
                    if (tempList.toArray().length > 0) { // condition, can only enters "eat" state if item list (tempList) != 0/null
                        String toString = "";
                        for (int i = 0; i < tempList.toArray().length; i++) { //provides a string w/ list of items, including nonConsumable(sword)
                            toString += tempList.toArray()[i] + "\n";
                        }
                        System.out.println("You have the following in your bag:");
                        System.out.println(toString);
                        System.out.println("Type which item you wish to consume");
                        eat = true;
                    } else {
                        System.out.println("\bYou do not have any consumables");
                        eat = false;
                    }
                } else if (inNum == 1) { //damage to boss
                    int dmg;
                    if (occupant.weapon) {
                        dmg = (int) (Math.random() * 10 + 100); //increase damage deal if occupant have weapon.
                    } else {
                        dmg = (int) (Math.random() * 10 + 1); //default dmg
                    }
                    hp = hp - dmg;
                    int npcdmg = (int) (Math.random() * 10 + 10); //boss(npc) dmg to player
                    php -= npcdmg;
                    System.out.println("You dealt " + dmg + " damage to the " + Boss[tempnum]);
                    System.out.println("The boss dealt " + npcdmg + " damage to you\n");
                }
            }
        }
        if(php <= 0) //player dies, game ends
        {
            System.out.println("You died");
            Runner.gameOff();
        }
    }
}







package People;

import java.util.ArrayList;
import java.util.Arrays;

import People.Person;
public class Items implements Consumable {
    private String status = "";
    public ArrayList inventory = new ArrayList(Arrays.asList(Person.equipment));
    private int hp;
    private boolean inStock = false;

    public void getItem(String stuff) { //obtains item, updates player equipment, through person,updateequip
        this.inventory.add(stuff);
        Person.updateEquip(this.inventory);
    }

    public int getHp(Person person) { //obtain then return player hp, used in beginning of fight.
        this.hp = person.php;
        return hp;
    }

    public void use(String item) { //checks if item is null or "" then restores health depending on the input, if contains bread hp + 10(max 100hp), if potion hp +30(no max), else cannot consume
        {
            if(item == null || item.equals("")) {
            }
            else
            {
                int x = 0;
                int tempHp = 0;
                String tempStr ="";
                if(item.toLowerCase().contains("bread"))
                {
                    tempStr = "bread";
                    tempHp = 10;
                }
                if(item.toLowerCase().contains("potion"))
                {
                    tempStr = "potion";
                    tempHp = 30;
                }
                if (item.toLowerCase().contains("sword"))
                {
                    System.out.println("Cannot consume sword");
                    return;
                }
                for (int i = 0; i < inventory.toArray().length; i++) {
                    if (inventory.toArray()[i].toString().contains(tempStr)) {
                        inStock = true;
                        x = i;
                    }
                }
                if (inStock) {
                    {
                        inventory.remove(x);
                        Person.updateEquip(this.inventory);
                        if(tempHp == 10)
                        {
                            if(this.hp <= 90)
                            {
                                this.hp += tempHp;
                            }
                            else
                            {
                                this.hp = 100;
                            }
                        }
                        if(tempHp == 30)
                        {
                            this.hp += tempHp;
                        }
                        Person.php = this.hp;
                        status = "Your health is now " + this.hp;
                        System.out.println(status);
                    }
                }
                else
                {
                    System.out.println("You do not have that item in the inventory");
                }
            }

        }
    }
}
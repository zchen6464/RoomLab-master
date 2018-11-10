package Rooms;

import People.Items;
import People.Person;

import java.util.ArrayList;

public class Room extends Items {
    public ArrayList tempList = inventory;
	public Person occupant;
	public int xLoc,yLoc;
	public String str = "empty";  //empty room
	public String state = " ";
	public String[] loot= {"loaf of wheat bread", "sword","bottle of health potion","pair of smelly old sock","cartoon of sour milk"};
	public Room(int x, int y)
	{
		xLoc = x;
		yLoc = y;
	}

	/**
	 * Method controls the results when a person enters this room.
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		if (str != "blocked")
		{
			occupant = x;
			x.setxLoc(this.xLoc);
			x.setyLoc(this.yLoc);
			if(occupant.turns > 0)
			{
				if(state.equals(" "))
				{
					if (str.equals("empty"))  //finds items/gear if room is empty
					{
						System.out.println("You enter a plain old room");
						int rng = (int) (Math.random() * 10);
						if (rng < 5) {
							System.out.println("You found a " + loot[rng]);
						}
						if (rng <= 3) {
							getItem(loot[rng].toLowerCase()); //stores loot if item == bread, potion, sword
							if (loot[rng].contains("sword"))   //if sword, weapon = true.
							{
								occupant.weapon = true;
							}
						}
					}
				}
			}
			state = "v";
			occupant.updateTurns();
		}
	}
	/**
	 * Removes the player from the room.
	 * @param x
	 */

	public void leaveRoom(Person x)
	{
		occupant = null;
	}
	
}

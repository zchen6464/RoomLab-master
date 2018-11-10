package People;

import java.util.ArrayList;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
	public static int turns = 0;
	String firstName;
	String familyName;
	int xLoc, yLoc;
	public static String[] equipment = {"loaf of wheat bread"};
	public static int php = 100;
	public boolean fight = false;
	public boolean weapon = false;


	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Person (String firstName, String familyName, int xLoc, int yLoc)
	{
		this.firstName = firstName;
		this.familyName = familyName;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	public static void updateEquip(ArrayList list)
	{
		list.toArray(equipment);
	}
	public static void updateTurns()
	{
		turns++;
	}
}

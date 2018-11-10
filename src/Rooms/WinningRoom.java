package Rooms;

import Game.Runner;
import People.Person;

public class WinningRoom extends Room
{
	public WinningRoom(int x, int y) {
			super(x, y);
			str = "nempty"; //not empty room
		}

		/**
		 * Triggers the game ending conditions.
		 * @param x the Person entering
		 */
	public void enterRoom(Person x) {
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		System.out.println("You found the exit. you have successfully escaped the dungeon"); //wins, game ends
		Runner.gameOff();
		}
}

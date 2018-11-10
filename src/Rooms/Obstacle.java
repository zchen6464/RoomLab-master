package Rooms;

import Game.Runner;
import People.Person;

public class Obstacle extends Room
{
    public Obstacle(int x, int y) {
        super(x, y);
        str = "blocked"; //room cannot be accessed
    }
    public void enterRoom(Person x) {
        System.out.println("The door is locked."); //msg when player try to enter room
    }
}
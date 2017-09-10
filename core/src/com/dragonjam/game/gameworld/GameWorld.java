package com.dragonjam.game.gameworld;


import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Drowner;
import com.dragonjam.game.gameobjects.Girl;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Updates game objects
 */

public class GameWorld {


	private Boy boy;
	private Girl girl;
	private Drowner mob;


	public GameWorld(int midPointX, int midPointY) {
		// Initialize game objects here.

		// Calculations for initail spawns of gameobjects.
		float boyInitX = midPointX - 20;
		float boyInitY = midPointY - 48;


		float girlInitX = midPointX - 5;
		float girlInitY = midPointY - 48 + 7;

		float middleForMobs = (midPointY - 48) + 10;

		// Create game objects.
		boy = new Boy(boyInitX, boyInitY, 27, 48);
		girl = new Girl(girlInitX, girlInitY, 27, 48);
		mob = new Drowner(0, middleForMobs, 27, 48, 15, 1.1f, true, 30);
	}

	public void update(float delta) {

		boy.update(delta);
		girl.update(delta);
		mob.update(delta);


	}

	public Girl getGirl() {
		return girl;
	}

	public Boy getBoy() {

		return boy;
	}

	public Drowner getMob() {

		return mob;
	}


}

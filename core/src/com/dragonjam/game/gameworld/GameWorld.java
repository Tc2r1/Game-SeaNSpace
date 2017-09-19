package com.dragonjam.game.gameworld;


import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Monster;
import com.dragonjam.game.gameobjects.MonsterHandler;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Updates game objects
 */

public class GameWorld {


	private Boy boy;
	private Girl girl;
	private Array<Monster> listOfMonsters;
	private boolean isAlive = true;
	private MonsterHandler monsterHandler;
	private int score = 0;
	private float gameWidth;


	public GameWorld(float gameWidth, int midPointY) {
		// Initialize game objects here.

		// Calculations for initial spawns of gameobjects.

		this.gameWidth = gameWidth;

		float midPointX = gameWidth / 2;

		float boyInitX = midPointX - 20;
		float boyInitY = midPointY - 48;


		float girlInitX = midPointX - 5;
		float girlInitY = midPointY - 48 + 7;

		float middleForMobs = (midPointY - 48) + 10;

		// Create game objects.
		boy = new Boy(boyInitX, boyInitY, 27, 48);
		girl = new Girl(girlInitX, girlInitY, 27, 48);


		monsterHandler = new MonsterHandler(middleForMobs, this);
		listOfMonsters = new Array<Monster>();
		listOfMonsters.addAll(monsterHandler.getMonsters());

	}

	public void update(float delta) {

		// Add a delta cap so that if our game takes too long
		// to update, we will not break our collision detection.

		if (delta > .15f) {
			delta = .15f;
		}
		boy.update(delta);
		girl.update(delta);
		monsterHandler.update(delta);
		monsterHandler.checkCollisions(boy, girl);

		if (isAlive == false) {

		}

	}

	public Girl getGirl() {
		return girl;
	}

	public Boy getBoy() {

		return boy;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public float getWidth() {
		return gameWidth;
	}



	public MonsterHandler getMonsterHandler() {
		return monsterHandler;
	}

}

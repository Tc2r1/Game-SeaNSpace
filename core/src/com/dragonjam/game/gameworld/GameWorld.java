package com.dragonjam.game.gameworld;


import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.FishHandler;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Monster;
import com.dragonjam.game.gameobjects.MonsterHandler;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Updates game objects
 */

public class GameWorld {

	private static int ACTOR_WIDTH = 52;
	private static int ACTOR_HEIGHT = 96;
	private static int PLAYER_MAX_HEALTH = 100;
	private Boy boy;
	private Girl girl;
	private Array<Monster> listOfMonsters;
	private boolean isAlive = true;
	private MonsterHandler monsterHandler;
	private FishHandler fishHandler;
	private int score = 0;
	private int playerCurrentHealth = 100;
	private float gameHeight;
	private float gameWidth;


	public GameWorld(float gameHeight, float gameWidth, int midPointX) {
		// Initialize game objects here.
		playerCurrentHealth = PLAYER_MAX_HEALTH;
		// Calculations for initial spawns of gameobjects.
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;

		float midPointY = gameHeight / 2;

		float boyInitX = midPointX - ACTOR_WIDTH/ 2 - 10;
		float boyInitY = midPointY - 48 - 90;

		float girlInitX = midPointX - ACTOR_WIDTH/ 2 + 16;
		float girlInitY = midPointY - 48 - 45;

		float middleForMobs = (midPointY - ACTOR_HEIGHT) + 5;

		// Create game objects.
		girl = new Girl(girlInitX, girlInitY, ACTOR_WIDTH, ACTOR_HEIGHT);
		boy = new Boy(boyInitX, boyInitY, ACTOR_WIDTH, ACTOR_HEIGHT);

		monsterHandler = new MonsterHandler(middleForMobs, this);
		fishHandler = new FishHandler(this);

		listOfMonsters = new Array<Monster>();
		listOfMonsters.addAll(monsterHandler.getMonsters());

	}

	public void update(float delta) {

		// Add a delta cap so that if our game takes too long
		// to update, we will not break our collision detection.

		if (delta > .15f) {
			delta = .15f;
		}
		girl.update(delta);
		boy.update(delta);
		monsterHandler.update(delta);
		monsterHandler.checkCollisions(boy, girl);
		fishHandler.update(delta);
		fishHandler.checkCollisions(boy, girl);

		// if player health is too low, they die.
		if (playerCurrentHealth < 1 && isAlive) {
			isAlive = false;
		}

		// if player is dead, game over.
		if (isAlive == false) {

		//	Gdx.app.log("PLAYER", "DEAD!");
		}

	}

	public int getActorWidth() {
		return ACTOR_WIDTH;
	}

	public int getActorHeight() {
		return ACTOR_HEIGHT;
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

	public void subtractDamage(int damageAmount) {
		playerCurrentHealth -= damageAmount;
	}

	public int getPlayerCurrentHealth() {
		return playerCurrentHealth;
	}

	public float getWidth() {
		return gameWidth;
	}
	public float getHeight() {
		return gameHeight;
	}


	public MonsterHandler getMonsterHandler() {
		return monsterHandler;
	}
	public FishHandler getFishHandler() {
		return fishHandler;
	}

}

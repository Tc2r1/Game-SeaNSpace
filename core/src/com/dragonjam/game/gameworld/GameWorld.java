package com.dragonjam.game.gameworld;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Drowner;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Monster;
import com.dragonjam.game.gameobjects.MonsterHandler;
import com.dragonjam.game.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Updates game objects
 */

public class GameWorld {


	private Boy boy;
	private Girl girl;
	private Drowner mob;
	private Array<Monster> listOfMonsters;
	private boolean isAlive = true;
	private MonsterHandler monsterHandler;
	private Random r;


	public GameWorld(int midPointX, int midPointY) {
		// Initialize game objects here.

		// Calculations for initial spawns of gameobjects.
		r = new Random();
		float boyInitX = midPointX - 20;
		float boyInitY = midPointY - 48;


		float girlInitX = midPointX - 5;
		float girlInitY = midPointY - 48 + 7;

		float middleForMobs = (midPointY - 48) + 10;

		// Create game objects.
		boy = new Boy(boyInitX, boyInitY, 27, 48);
		girl = new Girl(girlInitX, girlInitY, 27, 48);
		//mob = new Drowner(0, middleForMobs, 27, 48, 15, 1.1f, true, 30);

		listOfMonsters = new Array<Monster>();
		listOfMonsters.addAll(new MonsterHandler(middleForMobs).getDrowner());

		//monsterHandler = new MonsterHandler(middleForMobs);
	}

	public void update(float delta) {

		boy.update(delta);
		girl.update(delta);
		monsterHandler.update(delta);

		if (monsterHandler.collidesBoy(boy) || monsterHandler.collidesGirl(girl) && isAlive) {

			monsterHandler.getDrowner().onCollision();
			int randomEffect = r.nextInt(4);
			Sound playerHit = AssetLoader.playerHit01;
			switch (randomEffect) {
				case 0:
					playerHit = AssetLoader.playerHit01;
					break;
				case 1:
					playerHit = AssetLoader.playerHit02;
					break;
				case 2:
					playerHit = AssetLoader.playerHit03;
					break;
				case 3:
					playerHit = AssetLoader.playerHit04;
					break;
			}

			playerHit.play();
			isAlive = false;
		}


	}

	public Girl getGirl() {
		return girl;
	}

	public Boy getBoy() {

		return boy;
	}



	public MonsterHandler getMonsterHandler() {
		return monsterHandler;
	}

}

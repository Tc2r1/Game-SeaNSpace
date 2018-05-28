package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Tc2r on 9/10/2017.
 * <p>
 * Description: Handles the spawning and actions of fish.
 */

public class FishHandler {

	private final Array<Fish> fishs = new Array<Fish>();
	private Fish spawnFish;
	private boolean spawnLeft = true;
	private int startAmount = 10;
	private float gameHeight, gameWidth, maxSpawnHeight;
	private GameWorld gameWorld;
	Random random;


	public FishHandler(GameWorld gameWorld) {

		this.gameWorld = gameWorld;
		this.gameWidth = gameWorld.getWidth();
		this.gameHeight = gameWorld.getHeight();
		maxSpawnHeight = 50.0f;

		// set statistics for monsters here.
		spawnEngine(startAmount);

	}

	private void spawnEngine(int spawnAmount) {
		for (int i = 0; i < spawnAmount; i++) {
			random = new Random();

			spawnFish = new Fish(maxSpawnHeight , gameWidth) {
				@Override
				protected void die() {

				}

				@Override
				public int getHp() {
					return hp;
				}

				@Override
				public boolean collides(Boy boy) {
					return false;
				}

				@Override
				public boolean collides(Girl girl) {
					return false;
				}
			};
			addFish(spawnFish);
		}
	}

	public void update(float delta) {

		// update each monster
		for (Fish fish : fishs) {
			fish.update(delta);
		}


//		// If a monster is killed, increase the player's score and remove the monster.
//		for (Monster mons : monsters) {
//			if (mons.getHp() < 1 && mons.getIsAlive()) {
//				addScore(10);
//				mons.die();
//				monsters.removeValue(mons, false);
//			}
//		}

		// spawn infinite monsters
		if (fishs.size < startAmount) {
			spawnEngine(3);
		}

	}

	private void addScore(int i) {
		gameWorld.addScore(i);
	}

	public void addFish(Fish fish) {
		fishs.add(fish);
	}

	public Array<Fish> getFishs() {
		return fishs;
	}
}

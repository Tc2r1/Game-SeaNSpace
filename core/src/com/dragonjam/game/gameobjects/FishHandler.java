package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameworld.GameWorld;
import com.dragonjam.game.helpers.AssetLoader;

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
	private int startAmount = 3;
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
			boolean spawnLeft = random.nextBoolean();


//			switch (random.nextInt(3)){
//				case 0:
//				case 1:
//					spawnFish = new Drowner(spawnLeft, yPos, gameWidth);
//					Gdx.app.log("Spawning: ", "Drowner");
//					break;
//				case 2:
//					spawnMonster = new SandCrawler(spawnLeft, yPos, gameWidth);
//
//					Gdx.app.log("Spawning: ", "SandCrawler");
//					break;
//			};
			spawnFish = new Fish(spawnLeft, maxSpawnHeight , gameWidth) {
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

	public void checkCollisions(Boy boy, Girl girl) {
		for (Fish fish : fishs) {
			if (fish.collides(boy)) {
				fish.onCollide();
				int randomEffect = MathUtils.random(0, 4);
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

				gameWorld.subtractDamage(10);
				playerHit.play();
			}
			if (fish.collides(girl)) {
				fish.onCollide();
				int randomEffect = MathUtils.random(0, 4);
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

				gameWorld.subtractDamage(10);
				playerHit.play();
			}

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
		if (fishs.size < 2) {
			spawnEngine(5);
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

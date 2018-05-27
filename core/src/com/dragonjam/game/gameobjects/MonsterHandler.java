package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameworld.GameWorld;
import com.dragonjam.game.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Tc2r on 9/10/2017.
 * <p>
 * Description: Handles the spawning and colliding of all the monsters.
 */

public class MonsterHandler {

	private final Array<Monster> monsters = new Array<Monster>();
	private Monster spawnMonster;
	private boolean spawnLeft = true;
	private int startAmount = 1;
	private float yPos, gameWidth;
	private GameWorld gameWorld;
	Random random;


	public MonsterHandler(float yPos, GameWorld gameWorld) {

		this.gameWorld = gameWorld;
		this.yPos = yPos;
		this.gameWidth = gameWorld.getWidth();

		// set statistics for monsters here.
		monsterSpawnEngine(startAmount);

	}

	private void monsterSpawnEngine(int spawnAmount) {
		for (int i = 0; i < spawnAmount; i++) {
			random = new Random();
			boolean spawnLeft = random.nextBoolean();

			switch (random.nextInt(3)){
				case 0:
				case 1:
					spawnMonster = new Drowner(spawnLeft, yPos, gameWidth);
					Gdx.app.log("Spawning: ", "Drowner");
					break;
				case 2:
					spawnMonster = new SandCrawler(spawnLeft, yPos, gameWidth);

					Gdx.app.log("Spawning: ", "SandCrawler");
					break;
			};
			monsters.add(spawnMonster);
		}
	}

	public void checkCollisions(Boy boy, Girl girl) {
		for (Monster mob : monsters) {
			if (mob.collides(boy)) {
				mob.onCollide();
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
			if (mob.collides(girl)) {
				mob.onCollide();
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
		for (Monster mons : monsters) {
			mons.update(delta);
		}


		// If a monster is killed, increase the player's score and remove the monster.
		for (Monster mons : monsters) {
			if (mons.getHp() < 1 && mons.getIsAlive()) {
				addScore(10);
				mons.die();
				monsters.removeValue(mons, false);
			}
		}

		// spawn infinite monsters
		if (monsters.size < startAmount) {
			monsterSpawnEngine(5);
		}

	}

	private void addScore(int i) {
		gameWorld.addScore(i);
	}

	public void addMonster(Monster monster) {
		monsters.add(monster);
	}

	public Array<Monster> getMonsters() {
		return monsters;
	}
}

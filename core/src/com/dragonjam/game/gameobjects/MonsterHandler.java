package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.helpers.AssetLoader;

/**
 * Created by Tc2r on 9/10/2017.
 * <p>
 * Description: Handles the spawning and colliding of all the monsters.
 */

public class MonsterHandler {

	private Drowner drowner;
	private boolean spawnLeft = true;
	private int startAmount = 5;
	private final Array<Monster> monsters = new Array<Monster>();
	private float yPos, gameWidth;


	public MonsterHandler(float yPos, float gameWidth) {

		this.yPos = yPos;
		this.gameWidth = gameWidth;


		// set statistics for monsters here.
		monsterSpawnEngine(startAmount);

	}

	private void monsterSpawnEngine(int spawnAmount) {
		for (int i = 0; i < spawnAmount; i++) {
			Float x = 0f;
			Float y = MathUtils.random(yPos - 10.0f, yPos + 10.0f);
			int spawnSide = MathUtils.random(0, 1);
			Float basespeed = MathUtils.random(5, 30.0f);
			Float speedMod = MathUtils.random(.5f, 1.5f);
			int hp = (int) MathUtils.random(20.0f, 50.f);
			if (spawnSide == 1) {
				spawnLeft = true;
				x = MathUtils.random(-20, 3.0f);
			} else {
				// spawns from right side of the map.
				spawnLeft = false;
				x = MathUtils.random(gameWidth - 3.0f, gameWidth + 10.0f);

			}
			drowner = new Drowner(x, y, new Vector2(27, 48), basespeed, speedMod, spawnLeft, hp);
			monsters.add(drowner);
		}
	}

	public void checkCollisions(Boy boy, Girl girl) {
		for (Monster mob : monsters) {
			if (mob.collides(boy)) {
				mob.onCollide();
				int randomEffect = MathUtils.random(4);
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
			}
			if (mob.collides(girl)) {
				mob.onCollide();
				int randomEffect = MathUtils.random(4);
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
			}

		}
	}
	public void update(float delta) {

		// update each monster
		for (Monster mons : monsters) {
			mons.update(delta);
		}

		for (Monster mons : monsters) {
			if (mons.getHp() < 1 && mons.getIsAlive()) {
				mons.die();
				monsters.removeValue(mons, false);
			}
		}

		// spawn infinite monsters
		if (monsters.size < 2) {
			monsterSpawnEngine(5);

		}


		// Check how far mobs are to left or right, turn around accordingly.
	}

	public void addMonster(Monster monster) {
		monsters.add(monster);
	}

	public Array<Monster> getMonsters() {
		return monsters;
	}
}

package com.dragonjam.game.gameobjects;

/**
 * Created by Tc2r on 9/10/2017.
 * <p>
 * Description: Handles the spawning and colliding of all the monsters.
 */

public class MonsterHandler {

	private Drowner drowner;


	public MonsterHandler(float xPos) {

		drowner = new Drowner(0, xPos, 27, 48, 15, 1.1f, true, 30);
	}


	// A boolean to check if collision with boy has occured
	public boolean collidesBoy(Boy boy){
		return(drowner.collides(boy));

	}

	// Same with girl.
	public boolean collidesGirl(Girl girl){
		return (drowner.collides(girl));
	}

	public void update(float delta) {

		drowner.update(delta);


		// Check how far mobs are to left or right, turn around accordingly.
	}

	public Drowner getDrowner() {

		return drowner;
	}
}

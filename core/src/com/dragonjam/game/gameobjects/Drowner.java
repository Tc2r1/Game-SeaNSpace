package com.dragonjam.game.gameobjects;

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Drowner extends Monster {

	private Random r;

	public Drowner(float x, float y, int width, int height, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		super(x, y, width, height, baseSpeed, speedMod, startLeft, hp);


	}


	public void destroy() {
		this.destroy();
	}
}

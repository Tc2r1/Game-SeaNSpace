package com.dragonjam.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Monster;
import com.dragonjam.game.gameworld.GameRenderer;
import com.dragonjam.game.gameworld.GameWorld;

import java.util.ArrayList;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: handles all input while on the gamescreen.
 */

public class InputHandlers implements InputProcessor {

	private GameWorld gameWorld;
	private GameRenderer renderer;
	private ArrayList<Monster> listOfMonsters;
	private Boy boy;
	private Girl girl;


	public InputHandlers(GameWorld gameWorld, GameRenderer renderer) {
		this.gameWorld = gameWorld;
		this.renderer = renderer;
		this.boy = this.gameWorld.getBoy();
		this.girl = this.gameWorld.getGirl();

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	// On touch down we will check to see if the touch was on the collision box of a mob.
	// if touch is on the collision box of a mob, we take from it's health variable.
	// if the variable goes negative, it dies.

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("my", String.format("[Touch] x: %d, y: %d", screenX, screenY));
		Vector3 v = new Vector3(screenX, screenY, 0);
		renderer.getCam().unproject(v);
		Gdx.app.log("my", String.format("[S to VP] x: %.1f, y: %.1f", v.x, v.y));

		//boy.onClick();


		// if touch is above water have girl face that direction and Fire!
		if(v.y > 55){
			//Gdx.app.log("TOUCH: ", "ABOVE WATER");

			if (v.x < girl.getX() + girl.getWidth()/2){
		//		Gdx.app.log("TOUCH: ", "GIRL FACE LEFT");
				girl.setFaceLeft();
			} else {
		//		Gdx.app.log("TOUCH: ", "GIRL FACE RIGHT");
				girl.setFaceRight();
			}
			girl.fire();
		} else {
			// if touch is in water, have the boy attempt to pull in a fish.

			if (v.x < boy.getX() + boy.getWidth()/2) {
				boy.fishToTheLeft();
			} else {
				boy.fishToTheRight();
			}
		}

		// if girl clicked

		if (v.x > girl.getX() && v.x < girl.getX() + girl.getWidth()) {

			if (v.y > girl.getY() && v.y < girl.getY() + girl.getHeight()) {
				Gdx.app.log("Clicked: ", "girl");
				//girl.onClick(screenX, screenY, pointer, button);
			}
		}


		return true; // you return true to say we handled the touch.
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

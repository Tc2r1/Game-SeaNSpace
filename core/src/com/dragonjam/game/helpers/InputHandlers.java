package com.dragonjam.game.helpers;

import com.badlogic.gdx.InputProcessor;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Boy;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: handles all input while on the gamescreen.
 */

public class InputHandlers implements InputProcessor {

	private Boy boy;
	private Girl girl;


	public InputHandlers(Boy boy, Girl girl) {
		this.boy = boy;
		this.girl = girl;
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
		boy.onClick();
		girl.onClick();

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

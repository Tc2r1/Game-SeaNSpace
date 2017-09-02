package com.dragonjam.game.utility;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {
	
	public enum ButtonType {
		LEFT, RIGHT, MIDDLE, NONE
	}
	
	// Keys
	public static ArrayList<String> keysPressed = new ArrayList<String>();
	
	// Mouse
	public static Vector2 mousePos = new Vector2(-1.0f, -1.0f);
	public static Vector2 lastClick = new Vector2(-1.0f, -1.0f);
	public static ButtonType button = ButtonType.NONE;
	public static int scroll = 0;
	
	/**
	 * This class will listen for any and all action
	 * events, such as key presses, clicks, etc.
	 * To access the information from this class, simply
	 * call <code>InputHandler.lastClick</code> for example,
	 * to get a Vector2 object of where the user last
	 * clicked. All information can be accessed in a static
	 * manner.
	 * 
	 * @author Rane
	 */
	public InputHandler() {
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		// Add any keys that have been pressed
		// to the ArrayList
		
		if (keycode == Input.Keys.A) {
			keysPressed.add("a");
		}
		if (keycode == Input.Keys.D) {
			keysPressed.add("d");
		}
		if (keycode == Input.Keys.S) {
			keysPressed.add("s");
		}
		if (keycode == Input.Keys.W) {
			keysPressed.add("w");
		}
		
		if(keycode == Input.Keys.SPACE) {
			keysPressed.add(" ");
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		// When te key is released, remove
		// it from the ArrayList
		
		if (keycode == Input.Keys.A) {
			keysPressed.remove("a");
		}
		if (keycode == Input.Keys.D) {
			keysPressed.remove("d");
		}
		if (keycode == Input.Keys.S) {
			keysPressed.remove("s");
		}
		if (keycode == Input.Keys.W) {
			keysPressed.remove("w");
		}
		
		if(keycode == Input.Keys.SPACE) {
			keysPressed.remove(" ");
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		lastClick.x = screenX;
		lastClick.y = screenY;
		
		if(button == 0) {
			InputHandler.button = ButtonType.LEFT;
		} else if(button == 1) {
			InputHandler.button = ButtonType.RIGHT;
		} else if(button == 2) {
			InputHandler.button = ButtonType.MIDDLE;
		} else {
			InputHandler.button = ButtonType.NONE;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		lastClick.x = -1.0f;
		lastClick.y = -1.0f;
		InputHandler.button = ButtonType.NONE;
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mousePos.x = screenX;
		mousePos.y = screenY;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		scroll = amount;
		return false;
	}
	
	
}

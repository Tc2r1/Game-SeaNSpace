package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;


/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

/**
 * This element of the player is the character
 * that is will be fighting off monsters.
 *
 * @author Rane
 */
public class Girl{

	public enum State { STANDING, FIRING};
	public State currentState, previousState;
	private float stateTimer;

	private static final String TAG = "Girl";

	private float width, height;
	private boolean isFaceRight;
	private boolean isGirlFiring;
	private Vector2 position;
	private Rectangle collisionBox;
	private TextureRegion girlStand, currentRegion;
	private Animation<TextureRegion> girlFiring;

	// Constructor for the class
	public Girl(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		collisionBox = new Rectangle();

		// initialize state variables
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		isFaceRight = true;

		// create girl standing Texture region.
		girlStand = AssetLoader.girl;

		// create girl firing animation from texture atlas.
		girlFiring = AssetLoader.girlFiringAnim;
	}

	public void update(float delta) {
		currentRegion = getFrame(delta);
		collisionBox.set(position.x, position.y, width, height);

	}
	public void onDraw(SpriteBatch batch) {
		batch.draw(currentRegion, getX(), getY(), getWidth(), getHeight());
	}

	public State getState() {
		if(isGirlFiring){
			return State.FIRING;
		} else {
			return State.STANDING;
		}
	}

	public TextureRegion getFrame(float delta){
		// Get the current State of this actor.
		currentState = getState();
		TextureRegion region = girlStand;

		switch (currentState){
			case FIRING:
				region = girlFiring.getKeyFrame(stateTimer);
				if (girlFiring.isAnimationFinished(stateTimer) && previousState == State.FIRING) {

					Gdx.app.log(
							"Anim Duration" + girlFiring.getAnimationDuration() + ", STATE TIME: ", currentState.toString() + ":  " +stateTimer);
					isGirlFiring = false;
				}
				break;

			case STANDING:
				default:
					region = girlStand;
		}

		// set facing of girl based on isFaceRight switch.
		if (isFaceRight && region.isFlipX() == true) {
			region.flip(true, false);
		}
		if (!isFaceRight && region.isFlipX() == false){
			region.flip(true, false);
		}

		// update the stateTime and previous state. if state has changed, reset state time.
		if (currentState == previousState){
			stateTimer = stateTimer + delta;
		} else {
			stateTimer = 0;
		}
		previousState = currentState;

		return region;
	}

	public void onClick(int screenX, int screenY, int pointer, int button) {

		//AssetLoader.reload.play();
		// First of all, track which way to look.
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void fire(){
		isGirlFiring = true;
		AssetLoader.reload.play();
	}

	public void setFaceRight(){
		isFaceRight = true;
	}

	public void setFaceLeft(){
		isFaceRight = false;
	}

	public boolean isGirlFiring() {
		return isGirlFiring;
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public float getY() {
		return position.y;
	}

	public float getX() {
		return position.x;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}

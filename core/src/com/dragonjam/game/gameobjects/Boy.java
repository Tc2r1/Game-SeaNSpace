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
 * that is fishing off the shore and will
 * be catching fish.
 *
 * @author Rane
 */
public class Boy {

	public enum State{ SITTING, FISHING_LEFT, FISHING_RIGHT};
	public State currentState, previousState;
	private float stateTimer;

	private static final String TAG = "BOY";

	private float width, height;
	private boolean isFishingLeft;
	private boolean isFishingRight;
	private Vector2 position;
	private Rectangle collisionBox;
	private TextureRegion boySitting, currentRegion;
	private Animation<TextureRegion> boyFishingLeft, boyFishingRight;

	// Constructor for the class
	public Boy(float x, float y,float width, float height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		collisionBox = new Rectangle();

		// initialize state variables
		currentState = State.SITTING;
		previousState = State.SITTING;
		stateTimer = 0;
		isFishingLeft = false;
		isFishingRight = false;

		// create boy sitting Texture Region.
		boySitting = AssetLoader.boy;

		// create boy fishing Animations.
		boyFishingLeft = AssetLoader.boyFishingLeft;
		boyFishingRight = AssetLoader.boyFishingRight;
	}

	public void update(float delta) {
		currentRegion = getFrame(delta);
		collisionBox.set(position.x, position.y, width, height);

	}

	public void onDraw(SpriteBatch batch) {
		batch.draw(currentRegion, getX(), getY(), getWidth(), getHeight());
	}

	public State getState(){
		if(isFishingRight){
			return State.FISHING_RIGHT;
		}
		else if(isFishingLeft)
		{
			return State.FISHING_LEFT;
		} else
		{
			return State.SITTING;
		}
	}

	public TextureRegion getFrame(float delta){
		// Get the curent state of the boy.
		currentState = getState();
		TextureRegion region = boySitting;

		switch (currentState) {
			case FISHING_LEFT:
				region = boyFishingLeft.getKeyFrame(stateTimer);
				if (boyFishingLeft.isAnimationFinished(stateTimer) && previousState == State.FISHING_LEFT) {
					Gdx.app.log(
							"Anim Duration" + boyFishingLeft.getAnimationDuration() + ", STATE TIME: ", currentState.toString() + ":  " +stateTimer);

					isFishingLeft = false;
				}
				break;
			case FISHING_RIGHT:
				region = boyFishingRight.getKeyFrame(stateTimer);
				if (boyFishingRight.isAnimationFinished(stateTimer) && previousState == State.FISHING_RIGHT) {
					isFishingRight = false;
				}
				break;
			case SITTING:
			default:
				region = boySitting;

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

	public void onClick(SpriteBatch batch) {
		Gdx.app.log("TOUCHED", "BOY");
//		batch.setColor(1,1,0,1);
//		batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
//		batch.setColor(1,1,1,1);
//		//textureRegion.getTexture().


	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void fishToTheLeft(){
		isFishingLeft = true;
		// play splash sound
	}

	public void fishToTheRight(){
		isFishingRight = true;
		// play splash sound
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

package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjam.game.utility.Constants;
import com.dragonjam.game.utility.InputHandler;

public class Spooker extends Creature {
	
	// Dimensions of the texture for this creature
	private final int F_WIDTH = 0;
	private final int F_HEIGHT = 0;
	// TODO: Update to the actual size of each frame
	
	/**
	 * This is the main mob of the game that will
	 * randomly spawn and advance toward
	 * the player.
	 * 
	 * @author Rane
	 */
	public Spooker() {
		super(20, 10);
		// Create a creature with 20 health and 10 damage
		
		location.x = 100; // Start the attacker on the right
		state = 0;
		
		initAnimation();
	}
	
	public void update(float delta, PlayerWhole player) {
		
		this.time = this.time + delta;		
//		if(this.time > animation.getAnimationDuration()) {
//			this.time = 0.0f;
//		}
		
		// Pass in this y position, since we don't care about the
		// y in this case
		if((isInBounds(player.getAttacker().location.x - Constants.SPOOKER_RANGE, location.y)) || 
				(isInBounds(player.getAttacker().location.x + Constants.SPOOKER_RANGE, location.y))) {
			attack(player.getAttacker());
		}
		
		// ---- Input Handle ----
		// Mouse
		if(isInBounds(InputHandler.lastClick.x, InputHandler.lastClick.y)) {
			takeDamage(player.getAttack());
		}
		
	}
	
	@Override
	protected void initAnimation() {

		Texture spritesheet = new Texture(Gdx.files.internal("images/spritesheets/spooker.png"));
		// spriteGrid will break the sprite sheet into each
		// individual "frame". From there, we can load the
		// desired animation(s)
		TextureRegion[][] spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
		TextureRegion[] frames = new TextureRegion[5];
		
		// Breathing looking right (idle right)
		for(int f = 0; f < 5; f++) {
			// Row 0, columns 0-5
			frames[f] = spriteGrid[0][f];
		}
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
	}
	
	/**
	 * Checks to see if a given point is within this
	 * creature's hit-box / bounds.
	 * 
	 * @param pointX
	 * the horizontal position of the point
	 * @param pointY
	 * the vertical position of the point
	 * @return
	 * True, if the point is within the hit-box.
	 * Otherwise, false.
	 * 
	 * @author Rane
	 */
	private boolean isInBounds(float pointX, float pointY) {
		
		if((pointX > location.x - (F_WIDTH / 2)) && (pointX < location.x + (F_WIDTH / 2)) && 
				(pointY > location.y - (F_HEIGHT / 2) && (pointY < location.y + (F_HEIGHT)))) {
			return true;
		} else {
			return false;
		}
		
	}
	
	// Get / Set methods
	
	public int getF_WIDTH() {
		return F_WIDTH;
	}
	
	public int getF_HEIGHT() {
		return F_HEIGHT;
	}
	
}

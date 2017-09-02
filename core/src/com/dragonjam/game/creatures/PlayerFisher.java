package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerFisher extends Creature {
	
	// Dimensions of the texture for this creature
	private final int F_WIDTH = 172;
	private final int F_HEIGHT = 286;
	// TODO: Update to the actual size of each frame
	
	/**
	 * This element of the player is the character
	 * that is fishing off the shore and will
	 * be catching fish.
	 * 
	 * @author Rane
	 */
	public PlayerFisher() {
		super(100, 10);
		// Create a creature with 100 health and 10 damage
		
		initAnimation();
	}
	
	@Override
	public void update(float delta) {
		
		this.time = this.time + delta;		
//		if(this.time > animation.getAnimationDuration()) {
//			this.time = 0.0f;
//		}
		
		// ---- Input Handling ----
		// Mouse
		// TODO: Finish mouse for fisher
		
	}
	
	@Override
	protected void initAnimation() {

		Texture spritesheet = new Texture(Gdx.files.internal("images/spritesheets/fisher.png"));
		// spriteGrid will break the sprite sheet into each
		// individual "frame". From there, we can load the
		// desired animation(s)
		TextureRegion[][] spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
		TextureRegion[] frames = new TextureRegion[5];
		
		// Waiting for fish (idle)
		for(int f = 0; f < 5; f++) {
			// Row 0, columns 0-5
			frames[f] = spriteGrid[0][f];
		}
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
		// Casting
		for(int f = 0; f < 5; f++) {
			frames[f] = spriteGrid[1][f];
		}
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
		// Fish jerk left
		for(int f = 0; f < 5; f++) {
			frames[f] = spriteGrid[2][f];
		}
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
		// Fish jerk right
		for(int f = 0; f < 5; f++) {
			frames[f] = spriteGrid[3][f];
		}
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
	}
	
	// Get / Set methods
	
	public int getF_WIDTH() {
		return F_WIDTH;
	}
	
	public int getF_HEIGHT() {
		return F_HEIGHT;
	}
	
}

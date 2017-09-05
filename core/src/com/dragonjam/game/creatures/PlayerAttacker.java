package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjam.game.utility.Constants;
import com.dragonjam.game.utility.InputHandler;

public class PlayerAttacker extends Creature {
	
	// Dimensions of the texture for this creature
	private final int F_WIDTH = 300;
	private final int F_HEIGHT = 500;
	// TODO: Update to the actual size of each frame
	
	/**
	 * This element of the player is the character
	 * that will be attacking the mobs / UFO.
	 * 
	 * @author Rane
	 */
	public PlayerAttacker() {
		super(100, 10);
		// Create a creature with 100 health and 10 damage
		
		location.x = 100; // Start the attacker on the right
		state = 0;
		
		initAnimation();
	}
	
	@Override
	public void update(float delta) {
		
		this.time = this.time + delta;		
//		if(this.time > animation.getAnimationDuration()) {
//			this.time = 0.0f;
//		}
		
		// ---- Input updates ----
		// Mouse
		if((InputHandler.lastClick.x < (Constants.W_WIDTH / 2)) && 
				(InputHandler.button != InputHandler.ButtonType.NONE)) {
			// Check to see if the user clicked left of the screen
			// and make sure it's a valid mouse button
			location.x = -100;
			state = 1;
		} else if(InputHandler.button != InputHandler.ButtonType.NONE) {
			location.x = 100;
			state = 0;
		}
		
	}
	
	@Override
	protected void initAnimation() {

		Texture spritesheet = new Texture(Gdx.files.internal("images/spritesheets/attacker.png"));
		// spriteGrid will break the sprite sheet into each
		// individual "frame". From there, we can load the
		// desired animation(s)
		TextureRegion[][] spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
		TextureRegion[] frames = new TextureRegion[1];
		
		// ---- For single texture ----
		frames[0] = spriteGrid[0][0];
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
		// Redefine spriteGrid and frames, otherwise the 
		// other textures will be messed up / flipped
		spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
		frames = new TextureRegion[1];
		frames[0] = spriteGrid[0][0];
		frames[0].flip(true, false);
		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
		// ---- For animations ----
//		// Breathing looking right (idle right)
//		for(int f = 0; f < 5; f++) {
//			// Row 0, columns 0-5
//			frames[f] = spriteGrid[0][f];
//		}
//		animation.add(new Animation<TextureRegion>(0.33f, frames));
//		
//		// Attacking looking right
//		frames = new TextureRegion[5];
//		// You must redefine the array, otherwise the other
//		// animations will be changed
//		for(int f = 0; f < 5; f++) {
//			frames[f] = spriteGrid[1][f];
//		}
//		animation.add(new Animation<TextureRegion>(0.33f, frames));
//		
//		// Breathing looking left (idle left)
//		// Redefine the sprite grid, otherwise the previous
//		// animations will also be flipped
//		spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
//		frames = new TextureRegion[5];
//		for(int f = 0; f < 5; f++) {
//			frames[f] = spriteGrid[0][f];
//			// Flip the looking-right frames to look left
//			frames[f].flip(true, false);
//		}
//		animation.add(new Animation<TextureRegion>(0.33f, frames));
//		
//		// Attacking looking left
//		frames = new TextureRegion[5];
//		for(int f = 0; f < 5; f++) {
//			frames[f] = spriteGrid[1][f];
//			frames[f].flip(true, false);
//		}
//		animation.add(new Animation<TextureRegion>(0.33f, frames));
		
	}
	
	// Get / Set methods
	
	public int getF_WIDTH() {
		return F_WIDTH;
	}
	
	public int getF_HEIGHT() {
		return F_HEIGHT;
	}
	
}

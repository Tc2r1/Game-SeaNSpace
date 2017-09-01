package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.utility.Logger;

public class Creature {
	
	protected Animation<TextureRegion> animation;
	protected Vector2 location; // World location, not screen location
	
	protected double health = 0.0;
	protected double attack = 0.0;
	
	// Will be used to determine the correct animation frame
	protected float time = 0.0f;
	
	/**
	 * A base class used for any "creature" or
	 * object in the game that will be able
	 * to interact and effect the creature (ex. enemies,
	 * player, etc.)
	 * <br><br>
	 * This constructor will create a creature with
	 * the given health and attack.
	 * 
	 * @param hp
	 * amount of health the creature has before dying
	 * @param attack
	 * the amount of damage a creature does to another
	 * creature
	 * 
	 * @author Rane
	 */
	public Creature(double hp, double attack) {
		
		this.health = hp;
		this.attack = attack;
		
		location = new Vector2(0.0f, 0.0f);
		
	}
	
	/**
	 * A base class used for any "creature" or
	 * object in the game that will be able
	 * to interact and effect the creature (ex. enemies,
	 * player, etc.)
	 * <br><br>
	 * This constructor will create a blank creature
	 * with no properties defined.
	 * 
	 * @author Rane
	 */
	public Creature() {
		
		location = new Vector2(0.0f, 0.0f);
		
	}
	
	/**
	 * Should be called every time the screen is
	 * rendered. This can update aspects such
	 * as health, point, etc.
	 * 
	 * @param delta
	 * time since the last <code>render</code> call
	 * 
	 * @author Rane
	 */
	public void update(float delta) {
		
	}
	
	/**
	 * Method that should be called within the
	 * constructor of the higher level object 
	 * (For example, if the Player class extends
	 * from this, <code>initAnimation()</code>
	 * needs to be called within the Player
	 * constructor).
	 * <br><br>
	 * This method will load and define the
	 * animation(s) for this creature.
	 * 
	 * @author Rane
	 */
	protected void initAnimation() {
		Logger.log(this.getClass(), "initAnimation", Logger.Severity.WARNING, 
				"This method was not overwritten! The animation for this creature was most not loaded / loaded improperly!");
	}
	
	/**
	 * Will move the player based on the given
	 * directional changes.
	 * 
	 * @param dx
	 * change is horizontal position
	 * @param dy
	 * change in vertical position
	 * 
	 * @author Rane
	 */
	public void move(float dx, float dy) {
		this.location.add(dx, dy);
	}
	
	/**
	 * Will move the player based on the given
	 * directional changes.
	 * 
	 * @param movement
	 * the Vector2 that will be used to move the creature
	 * 
	 * @author Rane
	 */
	public void move(Vector2 movement) {
		this.location.add(movement);
	}
	
	/**
	 * Will inflict the amount of attack this creature
	 * has to the provided target.
	 * 
	 * @param target
	 * the Creature receiving the damage
	 * 
	 * @author Rane
	 */
	public void attack(Creature target) {
		target.takeDamage(attack);
	}
	
	/**
	 * Basically a <code>setHealth()</code> method,
	 * but made specifically for when this creature
	 * is attacked.
	 * 
	 * @param damage
	 * amount of health to subtract
	 * 
	 * @author Rane
	 */
	public void takeDamage(double damage) {
		this.health = this.health - damage;
	}
	
	
	// Get / Set methods
	
	
	public Animation<TextureRegion> getAnimation() {
		return animation;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	public void setLocation(Vector2 location) {
		this.location = location;
	}
	
	
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getAttack() {
		return attack;
	}
	public void setAttack(double attack) {
		this.attack = attack;
	}
	
}

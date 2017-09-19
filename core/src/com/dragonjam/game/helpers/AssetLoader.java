package com.dragonjam.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Handles the loading of assets.
 */

public class AssetLoader {

	public static Texture backgroundTexture, boyTexture;
	public static Texture drownerTexture;
	public static Texture eatingTexture;
	public static Texture girlTexture;

	public static Animation monsterAnimation;

	public static TextureRegion background;
	public static TextureRegion boy;
	public static TextureRegion girl;
	public static TextureRegion monsterAnim1, monsterAnim2, monsterAnim3,
					monsterAnim4, monsterAnim5, monsterAnim6;
	public static TextureRegion monsterEating;
	public static BitmapFont font, shadow;


	// sound effects
	public static Sound playerHit01, playerHit02, playerHit03, playerHit04;
	public static Sound reload, zombieHit01, zombieHit02, zombiedie01, zombiedie02, zombiedie03;

	public static void load() {


		// Load the textures from the images.
		boyTexture = new Texture(Gdx.files.internal("images/sprites/boy.png"));
		girlTexture = new Texture(Gdx.files.internal("images/sprites/girl.png"));
		drownerTexture = new Texture(Gdx.files.internal("images/sprites/drowner.png"));
		eatingTexture = new Texture(Gdx.files.internal("images/sprites/eating.png"));
		font = new BitmapFont(Gdx.files.internal("images/ui/text.fnt"), true);
		shadow = new BitmapFont(Gdx.files.internal("images/ui/shadow.fnt"), true);

		font.getData().setScale(.25f, -.25f);
		shadow.getData().setScale(.25f, -.25f);

		backgroundTexture = new Texture(Gdx.files.internal("images/background.png"));
		//backgroundTexture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

		// Add the background Texture Region.
		background = new TextureRegion(backgroundTexture);
		background.flip(true, false);


		// Add boy and girl texture regions.
		boy = new TextureRegion(boyTexture);

		girl = new TextureRegion(girlTexture);


		// monster eating Texture.
		monsterEating = new TextureRegion(eatingTexture);


		// create the animations.

		monsterAnim1 = new TextureRegion(drownerTexture, 2, 2, 300, 450);


		monsterAnim2 = new TextureRegion(drownerTexture, 304, 2, 300, 450);


		monsterAnim3 = new TextureRegion(drownerTexture, 606, 3, 300, 449);


		monsterAnim4 = new TextureRegion(drownerTexture, 1512, 8, 296, 444);


		monsterAnim5 = new TextureRegion(drownerTexture, 908, 4, 300, 448);


		monsterAnim6 = new TextureRegion(drownerTexture, 1210, 4, 300, 448);


		TextureRegion[] drownersFaceRight = {monsterAnim1, monsterAnim2, monsterAnim3, monsterAnim4, monsterAnim5, monsterAnim6};


		// Animates at .06 seconds per complete animation.
		monsterAnimation = new Animation(0.06f, drownersFaceRight);

		// pingpong adds a springy effect.
		monsterAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


		// Add sound effects!

		playerHit01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit01.mp3"));
		playerHit02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit02.mp3"));
		playerHit03 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit03.mp3"));
		playerHit04 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit04.mp3"));
		zombieHit01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/zombiehit1.mp3"));
		zombieHit02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/zombiehit2.mp3"));
		zombiedie01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/zombiedie1.mp3"));
		zombiedie02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/zombiedie2.mp3"));
		zombiedie03 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/zombiedie3.mp3"));

		reload = Gdx.audio.newSound(Gdx.files.internal("soundfxs/reload.mp3"));

	}

	public static void dispose() {
		// Dispose of all textures when we are done using them.
		boyTexture.dispose();
		girlTexture.dispose();
		drownerTexture.dispose();
		eatingTexture.dispose();
		backgroundTexture.dispose();
		playerHit01.dispose();
		playerHit02.dispose();
		playerHit03.dispose();
		playerHit04.dispose();
		reload.dispose();
		zombieHit01.dispose();
		zombieHit02.dispose();
		font.dispose();
		shadow.dispose();



	}

}

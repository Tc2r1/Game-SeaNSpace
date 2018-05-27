package com.dragonjam.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Handles the loading of assets.
 */

public class AssetLoader {

	public static Texture backgroundTexture, boyTexture, boyPullLTexture, boyPullRTexture;
	public static Texture drownerTexture;
	public static Texture sandCrawlerTexture;
	public static Texture eatingTexture;
	public static Texture girlTexture;

	public static Animation drownerAnimation, sandCrawlerAnimation, girlFiringAnim, boyFishingLeft, boyFishingRight;
	public static Animation fishBlackIdle, fishBlackSwim;
	public static TextureRegion background;
	public static TextureRegion boy;
	public static TextureRegion girl;
	public static TextureRegion drownerAnim1, drownerAnim2, drownerAnim3,
					drownerAnim4, drownerAnim5, drownerAnim6;
	public static TextureRegion drownerEating;
	public static BitmapFont font, shadow;


	// sound effects
	public static Sound playerHit01, playerHit02, playerHit03, playerHit04;
	public static Sound reload, drownerHit, drownerHit02, drownerDie01, drownerDie02, sandCrawlerDie01;

	public static void load() {


		// Load the textures from the images.
		boyTexture = new Texture(Gdx.files.internal("images/sprites/boy.png"));
		boyPullLTexture = new Texture(Gdx.files.internal("images/sprites/boy-left.png"));
		boyPullRTexture = new Texture(Gdx.files.internal("images/sprites/boy-right.png"));
		boyTexture = new Texture(Gdx.files.internal("images/sprites/boy.png"));
		girlTexture = new Texture(Gdx.files.internal("images/sprites/girl.png"));
		sandCrawlerTexture = new Texture(Gdx.files.internal("images/sprites/drowner.png"));
		drownerTexture = new Texture(Gdx.files.internal("images/sprites/drowner.png"));
		eatingTexture = new Texture(Gdx.files.internal("images/sprites/eating.png"));
		font = new BitmapFont(Gdx.files.internal("images/ui/text.fnt"), true);
		shadow = new BitmapFont(Gdx.files.internal("images/ui/shadow.fnt"), true);

		font.getData().setScale(.25f, -.25f);
		shadow.getData().setScale(.25f, -.25f);

		backgroundTexture = new Texture(Gdx.files.internal("images/background_h.png"));
		//backgroundTexture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

		// Add the background Texture Region.
		background = new TextureRegion(backgroundTexture);


		// Add boy and girl texture regions.
		boy = new TextureRegion(boyTexture);

		girl = new TextureRegion(girlTexture);


		// drowner eating Texture.
		drownerEating = new TextureRegion(eatingTexture);


		// create the animations.
		girlFiringAnim = new Animation(
				.05f,
				new TextureAtlas("images/sprites/firesprite.atlas").getRegions()
		);

		boyFishingLeft = new Animation(
				.2f,
				boy, new TextureRegion(boyPullRTexture), new TextureRegion(boyPullRTexture), boy
		);

		boyFishingRight = new Animation(
				.2f,
				boy, new TextureRegion(boyPullLTexture), new TextureRegion(boyPullLTexture), boy
		);

		// Animates at .06 seconds per complete animation.

		sandCrawlerAnimation = new Animation(.2f, new TextureAtlas("images/sprites/drowner.atlas").getRegions(), Animation.PlayMode.LOOP_PINGPONG);
		drownerAnimation = new Animation(.2f, new TextureAtlas("images/sprites/drowner.atlas").getRegions(), Animation.PlayMode.LOOP_PINGPONG);

		fishBlackSwim = new Animation(.1f, new TextureAtlas("images/sprites/fish_black_swim.atlas").findRegions("fish_black_swim"), Animation.PlayMode.LOOP_PINGPONG);
		fishBlackIdle = new Animation(.1f, new TextureAtlas("images/sprites/fish_black_idle.atlas").findRegions("fish_black_idle"), Animation.PlayMode.LOOP);


		// Add sound effects!

		playerHit01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit01.mp3"));
		playerHit02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit02.mp3"));
		playerHit03 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit03.mp3"));
		playerHit04 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/playerhit04.mp3"));
		drownerHit = Gdx.audio.newSound(Gdx.files.internal("soundfxs/drownerhit1.mp3"));
		drownerHit02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/drownerhit2.mp3"));
		drownerDie01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/drownerdie1.mp3"));
		drownerDie02 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/drownerdie2.mp3"));

		sandCrawlerDie01 = Gdx.audio.newSound(Gdx.files.internal("soundfxs/sandcrawlerdie1.mp3"));

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
		drownerHit.dispose();
		drownerHit02.dispose();
		drownerDie01.dispose();
		drownerDie02.dispose();
		sandCrawlerDie01.dispose();
		font.dispose();
		shadow.dispose();



	}

}

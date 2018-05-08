package com.capstonegroup5.flappywash;

/**
 * This class handles initializing the game with the correct screen size, starting the background
 * music, and creating a game state manager that is used throughout the game
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.capstonegroup5.flappywash.states.GameStateManager;
import com.capstonegroup5.flappywash.states.MenuState;

public class FlappyWash extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Wash";
	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;
	private Music music;
	// adan: i was here

	// This is Nat
	// handles background music and create objects to manage the game
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		img = new Texture("badlogic.jpg");
		gsm.push(new MenuState(gsm));


		Gdx.gl.glClearColor(1,0,0,1);
	}

	// draw to screen
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	// remove unused resources to prevent memory leaks
	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}

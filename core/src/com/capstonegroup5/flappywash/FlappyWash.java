package com.capstonegroup5.flappywash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	// adjfksdaj i was here

	// This is Nat
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		gsm.push(new MenuState(gsm));

		Gdx.gl.glClearColor(1,0,0,1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}

package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.capstonegroup5.flappywash.FlappyWash;

/**
 * Created by andrew on 1/23/18.
 *
 * This state contains a play button and waits for user input before switching to start the game
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Configurator configurator = Configurator.getInstance();

    // sets the camera and textures for the screen
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyWash.WIDTH/2, FlappyWash.HEIGHT/2);
        background = new Texture("bg1.png");
        playBtn = new Texture("playbtn.png");
    }

    // waits for input anywhere on the screen
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            configurator.setStartTime();
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    // draws resources on the screen based on screen bounds
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y);
        sb.end();
    }

    // dispose of resources after state changes to prevent memory leaks
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}

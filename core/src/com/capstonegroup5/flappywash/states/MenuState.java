package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.capstonegroup5.flappywash.FlappyWash;

/**
 * Created by andrew on 1/23/18.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0,0, FlappyWash.WIDTH, FlappyWash.HEIGHT);
        sb.draw(playBtn, FlappyWash.WIDTH/2 - playBtn.getWidth()/2, FlappyWash.HEIGHT/2 - playBtn.getHeight()/2);
        sb.end();
    }


    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}

package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.capstonegroup5.flappywash.FlappyWash;

/**
 * Created by andrew on 2/1/18.
 */

public class PlayState extends State {
    private Texture bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, FlappyWash.WIDTH/2, FlappyWash.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird, 50, 50);
        sb.end();
    }

    @Override
    public void displose() {

    }
}

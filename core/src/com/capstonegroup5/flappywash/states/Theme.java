package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by andrew on 2/26/18.
 */

public class Theme {

    private Texture bg;
    private Texture ground;

    public Theme(Texture bg, Texture ground) {
        this.bg = bg;
        this.ground = ground;
    }

    public Texture getBg() {
        return bg;
    }

    public Texture getGround() {
        return ground;
    }
}

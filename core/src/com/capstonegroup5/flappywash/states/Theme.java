package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by andrew on 2/26/18.
 */

public class Theme {

    private String bgPath;
    private String groundPath;

    public Theme(String bgPath, String groundPath) {
        this.bgPath = bgPath;
        this.groundPath = groundPath;
    }

    public Texture getBg() {
        return new Texture(bgPath);
    }

    public Texture getGround() {
        return new Texture(groundPath);
    }

    public String getBgPath() {
        return bgPath;
    }

    public String getGroundPath() {
        return  groundPath;
    }
}

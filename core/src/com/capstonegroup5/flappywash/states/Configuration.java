package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by andrew on 2/26/18.
 */

public class Configuration {

    private String bgPath;
    private String groundPath;
    private String topTubePath;
    private String bottomTubePath;
    private String characterPath;

    public Configuration(String bgPath, String groundPath, String topTubePath, String bottomTubePath, String characterPath) {
        this.bgPath = bgPath;
        this.groundPath = groundPath;
        this.topTubePath = topTubePath;
        this.bottomTubePath = bottomTubePath;
        this.characterPath = characterPath;
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

    public Texture getTopTube() { return new Texture(topTubePath); }

    public Texture getBottomTube() { return new Texture(bottomTubePath); }

    public Texture getCharacter() { return new Texture(characterPath); }
}

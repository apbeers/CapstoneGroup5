package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by andrew on 2/26/18.
 */

public class TextureLoader {

    private int themeIndex = 0;

    private ArrayList<Theme> themes = new ArrayList<Theme>();

    public TextureLoader() {

        themes.add(new Theme(new Texture("bg1.png"), new Texture("ground.png")));
        themes.add(new Theme(new Texture("bg.png"), new Texture("ground.png")));

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        themeIndex = new Random().nextInt((themes.size()));
    }

    public Texture getBg() {
        return themes.get(themeIndex).getBg();
    }

    public Texture getGround() {
        return themes.get(themeIndex).getGround();
    }
}


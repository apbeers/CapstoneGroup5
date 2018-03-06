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


        themes.add(new Theme("bg.png", "ground.png"));
        themes.add(new Theme("bg1.png", "ground.png"));
        themes.add(new Theme("bg2.png","ground.png"));
        themes.add(new Theme("bg3.png","ground.png"));

        themeIndex = new Random().nextInt((themes.size()));
    }

    public Texture getBg() {
        return themes.get(themeIndex).getBg();
    }

    public String getBgFilepath() {
        return  themes.get(themeIndex).getBgPath();
    }

    public String getGroundFilepath() {
        return themes.get(themeIndex).getGroundPath();
    }

    public Texture getGround() {
        return themes.get(themeIndex).getGround();
    }
}


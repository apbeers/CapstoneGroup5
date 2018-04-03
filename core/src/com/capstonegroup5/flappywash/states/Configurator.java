package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

import sun.security.krb5.Config;

/**
 * Created by andrew on 2/26/18.
 */

public class Configurator {

    private static Configurator instance = null;

    private static int themeIndex = 0;
    private static ArrayList<Configuration> configurations = new ArrayList<Configuration>();

    protected Configurator() {
        configurations.add(new Configuration("bg.png", "ground.png"));
        configurations.add(new Configuration("bg1.png", "ground.png"));
        configurations.add(new Configuration("bg2.png","ground.png"));
        configurations.add(new Configuration("bg3.png","ground.png"));

    }

    public static Configurator getInstance() {
        if (instance == null) {
            instance = new Configurator();
        }
        themeIndex = new Random().nextInt((configurations.size()));
        return instance;
    }

    public Texture getBg() {
        return configurations.get(themeIndex).getBg();
    }

    public String getBgFilepath() {
        return  configurations.get(themeIndex).getBgPath();
    }

    public String getGroundFilepath() {
        return configurations.get(themeIndex).getGroundPath();
    }

    public Texture getGround() {
        return configurations.get(themeIndex).getGround();
    }
}


package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

import io.keen.client.java.JavaKeenClientBuilder;
import io.keen.client.java.KeenClient;
import sun.security.krb5.Config;

import java.util.HashMap;
import java.util.Map;

import io.keen.client.java.JavaKeenClientBuilder;
import io.keen.client.java.KeenClient;
import io.keen.client.java.KeenProject;

/**
 * Created by andrew on 2/26/18.
 */

public class Configurator {

    private static final int minTubeSpacing = 80;
    private static final int maxTubeSpacing = 160;
    private static final int minTubeCount = 1;
    private static final int maxTubeCount = 8;

    private static Map<String, Object> event = new HashMap<String, Object>();

    private static int themeIndex = 0;
    private static ArrayList<Configuration> configurations = new ArrayList<Configuration>();

    private static Configurator instance = null;

    protected Configurator() {

        KeenClient client = new JavaKeenClientBuilder().build();

        KeenClient.initialize(client);
        KeenProject project = new KeenProject("5a9eabf6c9e77c00018ed78b", "F4B1E0FAB90CF908CC43F7B6C4DFB6AEC4D19EA05693878493F2D0EAAA3003B5E29D1836BA98C3C2C6350E39A448D9277523F1B05F0251F725798AA2E2592C6C9532F8BDF679CE361D82DB94DDAED478FBC4E6E44C8C82BA0C018DD54C2BF6EB", null);
        KeenClient.client().setDefaultProject(project);

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

        instance.addEvent("Background Image", configurations.get(themeIndex).getBgPath());
        instance.addEvent("Ground Image", configurations.get(themeIndex).getGroundPath());

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

    public int getTubeSpacing() {
        return 0;
    }

    public int getTubeCount() {
        return 0;
    }

    public void addEvent(String key, String value) {
        event.put(key, value);
    }

    public void sendData() {

        KeenClient.client().addEvent("Game Statistics", event);
        event.clear();
    }
}


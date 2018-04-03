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

    private static final int minTubeSpacing = 50;
    private static final int maxTubeSpacing = 200;
    private static final int minTubeCount = 1;
    private static final int maxTubeCount = 12;
    private static final int minFluctuation = 50;
    private static final int maxFluxtuation = 200;
    private static final int minTubeGap = 100;
    private static final int maxtubeGap = 300;
    private static final int minBirdGravity = 6;
    private static final int maxBirdGravity = 18;
    private static final int minbirdMovement = 50;
    private static final int maxBirdMovement = 150;

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

    public Texture getGround() {
        return configurations.get(themeIndex).getGround();
    }

    public int getTubeSpacing() {
        int tubeSpacing = new Random().nextInt((maxTubeSpacing - minTubeSpacing) + 1) + minTubeSpacing;
        instance.addEvent("Tube Spacing", Integer.toString(tubeSpacing) );
        return tubeSpacing;
    }

    public int getTubeCount() {
        int tubeCount = new Random().nextInt((maxTubeCount - minTubeCount) + 1) + minTubeCount;
        instance.addEvent("Tube Count", Integer.toString(tubeCount) );
        return tubeCount;
    }

    public int getTubeFluctuation() {
        int fluctuation = new Random().nextInt((maxFluxtuation - minFluctuation) + 1) + minFluctuation;
        instance.addEvent("Tube Fluctuation", Integer.toString(fluctuation) );
        return fluctuation;
    }

    public int getTubeGap() {
        int tubeGap = new Random().nextInt((maxtubeGap - minTubeGap) + 1) + minTubeGap;
        instance.addEvent("Tube Gap", Integer.toString(tubeGap) );
        return tubeGap;
    }

    public int getBirdGravity() {
        int birdGravity = new Random().nextInt((maxBirdGravity - minBirdGravity) + 1) + minBirdGravity;
        instance.addEvent("Bird Gravity", Integer.toString(birdGravity) );
        return birdGravity;
    }

    public int getBirdMovement() {
        int birdMovement = new Random().nextInt((maxBirdMovement - minbirdMovement) + 1) + minbirdMovement;
        instance.addEvent("Bird Movement", Integer.toString(birdMovement) );
        return birdMovement;
    }

    public void addEvent(String key, String value) {
        event.put(key, value);
    }

    public void sendData() {

        KeenClient.client().addEvent("Game Statistics", event);
        event.clear();
    }
}

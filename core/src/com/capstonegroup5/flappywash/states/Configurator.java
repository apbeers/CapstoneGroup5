package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.keen.client.java.JavaKeenClientBuilder;
import io.keen.client.java.KeenClient;
import io.keen.client.java.KeenProject;

/**
 * Created by andrew on 2/26/18.
 *
 * This class is meant to be used as a singleton. It contains an array of various configurations
 * that are randomly chosen every time the level restarts. It also randomizes many other
 * characteristics to add variety to the game
 *
 * This class also handles most of the analytics. The configuration and playtime for each game is
 * logged to later determine which settings result in a playtime of about 30 seconds
 *
 * It also handles keeping up with how long the game has run. After 30 seconds, the user is returned
 * to the main screen
 */

public class Configurator {

    private boolean groundBird = true;
    private long startTime;
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

        // Keen.io is the analytics provider. this initializes an instance from their libraries
        KeenClient client = new JavaKeenClientBuilder().build();
        KeenClient.initialize(client);

        // sets our project's id and write key
        KeenProject project = new KeenProject("5a9eabf6c9e77c00018ed78b", "F4B1E0FAB90CF908CC43F7B6C4DFB6AEC4D19EA05693878493F2D0EAAA3003B5E29D1836BA98C3C2C6350E39A448D9277523F1B05F0251F725798AA2E2592C6C9532F8BDF679CE361D82DB94DDAED478FBC4E6E44C8C82BA0C018DD54C2BF6EB", null);
        KeenClient.client().setDefaultProject(project);

        // each of these configurations is manually created and chosen at random when the game starts
        configurations.add(new Configuration("bg1.png", "ground.png", "toptube2.png", "bottomtube2.png", "purplebubble.png"));
        configurations.add(new Configuration("bg2.png", "ground.png", "toptube2.png", "bottomtube2.png", "greenbubble.png"));
        configurations.add(new Configuration("bg3.png","ground.png", "toptube2.png", "bottomtube2.png", "pooanimation.png"));
        configurations.add(new Configuration("bg4.png","ground.png", "toptube2.png", "bottomtube2.png", "yellowbubble.png"));
        configurations.add(new Configuration("bg2.png", "ground.png", "toptube2.png", "bottomtube2.png", "bluebubble.png"));
        configurations.add(new Configuration("bg3.png","ground.png", "toptube2.png", "bottomtube2.png", "redbubble.png"));
        configurations.add(new Configuration("bg4.png","ground.png", "toptube2.png", "bottomtube2.png", "orangebubble.png"));
    }

    // instantiates a single instance of the class and returns it
    public static Configurator getInstance() {

        if (instance == null) {
            instance = new Configurator();
        }
        themeIndex = new Random().nextInt((configurations.size()));

        instance.addEvent("background_image", configurations.get(themeIndex).getBgPath());
        instance.addEvent("ground_image", configurations.get(themeIndex).getGroundPath());

        return instance;
    }

    public Texture getBg() {
        return configurations.get(themeIndex).getBg();
    }

    public Texture getGround() {
        return configurations.get(themeIndex).getGround();
    }

    // sets the game mode to flappy-bird like or with a ground character and logs it to keen.io
    public void setGameModeRandomly() {
        groundBird = new Random().nextBoolean();
        if (groundBird) {
            addEvent("game_mode", "ground");
        } else {
            addEvent("game_mode", "air");
        }
        //groundBird = true;
    }

    public boolean getGameMode() {
        return groundBird;
    }

    // returns a randomized tube spacing within the specified ranges
    public int getTubeSpacing() {
        if (groundBird) {
            return 200;
        } else {
            int tubeSpacing = new Random().nextInt((maxTubeSpacing - minTubeSpacing) + 1) + minTubeSpacing;
            instance.addEvent("tube_spacing", Integer.toString(tubeSpacing) );
            return tubeSpacing;
        }
    }

    // returns a randomized tube cound within the specified ranges
    public int getTubeCount() {
        if (groundBird) {
            return 2;
        } else {
            int tubeCount = new Random().nextInt((maxTubeCount - minTubeCount) + 1) + minTubeCount;
            instance.addEvent("tube_count", Integer.toString(tubeCount) );
            return tubeCount;
        }
    }

    // returns a randomized tube fluctuation within the specified ranges
    public int getTubeFluctuation() {
        if (groundBird) {
            return 100;
        } else {
            int fluctuation = new Random().nextInt((maxFluxtuation - minFluctuation) + 1) + minFluctuation;
            instance.addEvent("tube_fluctuation", Integer.toString(fluctuation) );
            return fluctuation;
        }
    }

    // returns a randomized tube gap within the specified ranges
    public int getTubeGap() {
        if (groundBird) {
            return 250;
        } else {
            int tubeGap = new Random().nextInt((maxtubeGap - minTubeGap) + 1) + minTubeGap;
            instance.addEvent("tube_gap", Integer.toString(tubeGap) );
            return tubeGap;
        }
    }

    // returns a randomized character gravity within the specified ranges
    public int getBirdGravity() {
        if (groundBird) {
            return 5;
        } else {
            int birdGravity = new Random().nextInt((maxBirdGravity - minBirdGravity) + 1) + minBirdGravity;
            instance.addEvent("bird_gravity", Integer.toString(birdGravity) );
            return birdGravity;
        }
    }

    // returns a randomized character movement within the specified ranges
    public int getBirdMovement() {
        if (groundBird) {
            return 100;
        } else {
            int birdMovement = new Random().nextInt((maxBirdMovement - minbirdMovement) + 1) + minbirdMovement;
            instance.addEvent("bird_movement", Integer.toString(birdMovement) );
            return birdMovement;
        }
    }

    // retuns the texture for the top tube based on the randomly chosen configuration
    public Texture getTopTube() { return configurations.get(themeIndex).getTopTube(); }

    // retuns the texture for the botton tube based on the randomly chosen configuration

    public Texture getBottomTube() { return configurations.get(themeIndex).getBottomTube(); }

    // retuns the texture for character based on the randomly chosen configuration and game mode
    public Texture getCharacter() {
        if (getGameMode()) {
            return new Texture("groundbubble.png");
        } else {
            return  configurations.get(themeIndex).getCharacter();
        }
    }

    // used to add evens to keen.io
    public void addEvent(String key, String value) {
        event.put(key, value);
    }

    // send all of the events in the event hashmap
    public void sendData() {

        KeenClient.client().addEvent("game_statistics", event);
        event.clear();
    }

    // store start time when the player leaves the main menu and starts playing
    public void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    // returns the playtime so theat the state mangager can exit the game after 30 seconds
    public long getPlayTime() {
        return System.currentTimeMillis() - startTime;
    }
}

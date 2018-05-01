package com.capstonegroup5.flappywash.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.capstonegroup5.flappywash.states.Configurator;

import java.util.Random;

/**
 * Created by Alec on 2/3/2018.
 *
 * Creates and manages the tube objects in the level and handles some collision detection
 */

public class Tube {

    public Configurator configurator = Configurator.getInstance();

    public static final int TUBE_WIDTH = 52;

    private static int fluctuation;
    private static int tubeGap;
    private static final int LOWEST_OPENING = 50;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    // creates the top and bottom tubes
    public Tube(float x) {

        fluctuation = configurator.getTubeFluctuation();
        tubeGap = configurator.getTubeGap();

        topTube = configurator.getTopTube();
        bottomTube = configurator.getBottomTube();

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(fluctuation) + tubeGap + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - tubeGap - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    // handles moving the tubes across the screen as the game runs
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(fluctuation) + tubeGap + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - tubeGap - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    // returns if the character has collided with a tube
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    // disposes of resources to prevent memory leaks
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}

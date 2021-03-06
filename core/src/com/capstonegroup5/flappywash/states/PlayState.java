package com.capstonegroup5.flappywash.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.capstonegroup5.flappywash.FlappyWash;
import com.capstonegroup5.flappywash.sprite.Bird;
import com.capstonegroup5.flappywash.sprite.Tube;

import java.util.HashMap;
import java.util.Map;

import io.keen.client.java.JavaKeenClientBuilder;
import io.keen.client.java.KeenClient;
import io.keen.client.java.KeenProject;

/**
 * Created by andrew on 2/1/18.
 *
 * This is the state while the player is playing the game. It handles allocating the various
 * elements on screen and transitioning states after collisions occur
 */

public class PlayState extends State {
    private static int tubeSpacing;
    private static int tubeCount;
    private static final int GROUND_Y_OFFSET = -50;

    private KeenClient client = new JavaKeenClientBuilder().build();

    // gets and instance of the configurator
    private Configurator configurator = Configurator.getInstance();
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Map<String, Object> event = new HashMap<String, Object>();
    private Array<Tube> tubes;
    private long startTime;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        // starts the bird on the ground or in the air based on the game mode chosen
        configurator.setGameModeRandomly();
        if (configurator.getGameMode()) {
            bird = new Bird(50, 80);
        } else {
            bird = new Bird(50, 300);
        }

        cam.setToOrtho(false, FlappyWash.WIDTH/2, FlappyWash.HEIGHT/2);
        KeenClient.initialize(client);
        KeenProject project = new KeenProject("5a9eabf6c9e77c00018ed78b", "F4B1E0FAB90CF908CC43F7B6C4DFB6AEC4D19EA05693878493F2D0EAAA3003B5E29D1836BA98C3C2C6350E39A448D9277523F1B05F0251F725798AA2E2592C6C9532F8BDF679CE361D82DB94DDAED478FBC4E6E44C8C82BA0C018DD54C2BF6EB", null);
        KeenClient.client().setDefaultProject(project);

        startTime = System.currentTimeMillis();

        tubeSpacing = configurator.getTubeSpacing();
        tubeCount = configurator.getTubeCount();

        bg = configurator.getBg();
        ground = configurator.getGround();
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth /2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth /2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for(int i = 1; i <= tubeCount; i++)
        {
            tubes.add(new Tube(i * (tubeSpacing + Tube.TUBE_WIDTH)));
        }
    }

    // handles input
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    // called on each frame to move objects across the screen and check for collisions, as well
    // as checking play time
    @Override
    public void update(float dt) {
        handleInput();
        updateGround();

        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        // loops through all of the thube to move them and check collisions
        for(int i = 0; i < tubes.size; i++)
        {
            Tube tube = tubes.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + tubeSpacing) * tubeCount));
            }

            if(tube.collides(bird.getBounds()))
            {
                gsm.set(new PlayState(gsm));
            }
        }

        // restarts the game if the bird touches the ground
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gsm.set(new PlayState(gsm));
        }

        if (configurator.getPlayTime() > 30000) {
            gsm.set(new MenuState(gsm));
        }

        cam.update();
    }

    // draws elements on the screen
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth /2),0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    // called when the level restarts
    // disposes of resources and logs the playtime on that level
    @Override
    public void dispose() {

        long playTime = System.currentTimeMillis() - startTime;
        configurator.addEvent("play_time", String.valueOf(playTime));
        configurator.sendData();

        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes)
        {
            tube.dispose();
        }
    }

    // moves the ground horizontally across the screen
    private void updateGround() {
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() *2, 0);

        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() *2, 0);
    }
}

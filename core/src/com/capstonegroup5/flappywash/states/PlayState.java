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
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    KeenClient client = new JavaKeenClientBuilder().build();
// Android Client:
// KeenClient client = new AndroidKeenClientBuilder(this).build();

    private Configurator configurator = Configurator.getInstance();
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Map<String, Object> event = new HashMap<String, Object>();
    private Array<Tube> tubes;
    private long starTime;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyWash.WIDTH/2, FlappyWash.HEIGHT/2);
        KeenClient.initialize(client);
        KeenProject project = new KeenProject("5a9eabf6c9e77c00018ed78b", "F4B1E0FAB90CF908CC43F7B6C4DFB6AEC4D19EA05693878493F2D0EAAA3003B5E29D1836BA98C3C2C6350E39A448D9277523F1B05F0251F725798AA2E2592C6C9532F8BDF679CE361D82DB94DDAED478FBC4E6E44C8C82BA0C018DD54C2BF6EB", null);
        KeenClient.client().setDefaultProject(project);

        starTime = System.currentTimeMillis();

        bg = configurator.getBg();
        ground = configurator.getGround();
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth /2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth /2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }



    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i++)
        {
            Tube tube = tubes.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds()))
            {
                gsm.set(new PlayState(gsm));
            }
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));
        cam.update();

    }

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

    @Override
    public void dispose() {

        long playTime = System.currentTimeMillis() - starTime;
        configurator.addEvent("Play Time", String.valueOf(playTime));
        configurator.sendData();

        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes)
        {
            tube.dispose();
        }
    }

    private void updateGround() {
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() *2, 0);

        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() *2, 0);


    }
}

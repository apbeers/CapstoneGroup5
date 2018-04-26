package com.capstonegroup5.flappywash.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.capstonegroup5.flappywash.states.Configurator;

/**
 * Created by Natalia Baker on 2/1/2018.
 */

public class Bird {
    private Configurator configurator = Configurator.getInstance();
    private static int gravity;
    private static int movement;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;

    public Bird(int x, int y){
      position = new Vector3(x, y, 0);
      velocity = new Vector3(0, 0,0 );
      texture = configurator.getCharacter();
      birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
      bounds = new Rectangle(x,y, texture.getWidth() / 3, texture.getHeight());
      flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

      gravity = configurator.getBirdGravity() * -1;
      movement = configurator.getBirdMovement();
    }

    public void update(float dt){
        birdAnimation.update(dt);

        System.out.println(position.y);

        if (configurator.getGameMode()) {
            if (position.y <= 80 && velocity.y < 0) {
                velocity.add(0,0,0);
                velocity.scl(dt);
                position.add(movement * dt, 0,0);
                position.add(0, 0, 0);
                if(position.y < 0)
                    position.y = 0;

                velocity.scl(1/dt);
                bounds.setPosition(position.x, position.y);
            }
            else if(position.y > 0) {
                velocity.add(0, gravity, 0);
                velocity.scl(dt);
                position.add(movement * dt, velocity.y,0);
                position.add(0, velocity.y, 0);
                if(position.y < 0)
                    position.y = 0;

                velocity.scl(1/dt);
                bounds.setPosition(position.x, position.y);
            }
        } else {
            if(position.y > 0) {
                velocity.add(0, gravity, 0);
                velocity.scl(dt);
                position.add(movement * dt, velocity.y,0);
                position.add(0, velocity.y, 0);
                if(position.y < 0)
                    position.y = 0;

                velocity.scl(1/dt);
                bounds.setPosition(position.x, position.y);
            }
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        if (configurator.getGameMode()) {
            if (position.y <= 80) {
                velocity.y = 225;
            }
        } else {
            velocity.y = 225;
        }
        flap.play(0.5f);
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}

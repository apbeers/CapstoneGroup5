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
 *
 * Handles the in-game character's behavior including gravity, velocity, collision detection.
 * It also handles the multiple behaviors of the character during two different game styles
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

    //Creates the bird character using the Animation class, combines it with the sound effects,
    // and adds it to the screen
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

    // gets called every frame to update the birds location. it can handle both game modes
    public void update(float dt){
        birdAnimation.update(dt);

        // contains behavior for the main game mode, where the character moves along the ground
        // and can only single-jump over obstacles. It does this by zeroing out the character's
        // vertical veloicty when it appreaches the ground, and only allows the jump action
        // if the bird is not in contact with the ground/
        if (configurator.getGameMode()) {
            if (position.y <= 75 && velocity.y < 0) {
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

        // handles the gameplay that is similar to flappy-bird where the character is allowed to fly
        // and dies if it hits the ground
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

    // returns the character's 2d position
    public Vector3 getPosition() {
        return position;
    }

    // returns the new texture for the character's animation
    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    // controls when the character is allowed to jump, and sets the velocity accordingly
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

    // returns the character's bounds for collision detection
    public Rectangle getBounds()
    {
        return bounds;
    }

    // disposes of used resources to prevent memory leaks
    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}

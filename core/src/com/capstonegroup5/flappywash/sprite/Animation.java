package com.capstonegroup5.flappywash.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
/**
 * Created by adanrutiaga on 2/5/18.
 *
 * Handles generating and animating the in-game character
 */


public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    // build the game cahracter by splitting the image into 3 sections, then animating
    // between them as the game runs
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    // increment the frame of the character that is showing
    public void update(float dt) {
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

}

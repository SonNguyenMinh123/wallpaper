package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 13/05/2016.
 */
public class Rabbit {

    private Array<TextureAtlas.AtlasRegion> rabbits;
    private Animation animation;

    private Vector2 pos;
    private Vector2 velocity;
    private Vector2 size;
    private Vector2 acceleration;
    private float rotation, rot;
    private int type, state, stand, h, speedX;

    public Rabbit() {
        type = Constant.RABBIT_LEFT;
        int width = Constant.RABBIT_WIDTH;
        int height = (int) (Constant.RABBIT_WIDTH / 1.2f);
        this.size = new Vector2(width, height);
        pos = new Vector2(0, 0);
        speedX = 150;
        this.velocity = new Vector2(speedX, 0);
        update();
        state = Constant.RABBIT_RUN;
    }

    public void updateAnimation() {
        animation = new Animation(0.09f, rabbits);
        animation.setPlayMode(Animation.LOOP);
    }

    public void update() {
        if (type == Constant.RABBIT_LEFT) {
            rabbits = Assets.assetRabbit.rabbit1;
            pos.x = -size.x;
        } else {
            rabbits = Assets.assetRabbit.rabbit2;
            pos.x = Constant.WIDTH * 2;
        }
        pos.y = Constant.GRASS_Y + Constant.GRASS_HEIGHT / 6;
        velocity.x = type * speedX;
        updateAnimation();
    }

    public void draw(SpriteBatch batch, float runTime, int mPos) {
        switch (state) {
            case Constant.RABBIT_RUN:
                run(batch, runTime, mPos);
                break;
            case Constant.RABBIT_TURN:
                turn();
                break;
        }
    }

    public void run(SpriteBatch batch, float runTime, int mPos) {
        batch.draw(animation.getKeyFrame(runTime), pos.x + mPos, this.pos.y, size.x, size.y);
        pos.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (checkRun()) {
            type *= -1;
            state = Constant.RABBIT_TURN;
        }
    }

    public void turn() {
        update();
        state = Constant.RABBIT_RUN;
    }

    public boolean checkRun() {
        if (type == Constant.RABBIT_LEFT && pos.x > Constant.WIDTH * 10 ||
                type == Constant.RABBIT_RIGHT && pos.x < -Constant.WIDTH * 10) {
            return true;
        }
        return false;
    }

    public void onTouch() {
        Log.e("TOUCH", "OKE");
    }
}

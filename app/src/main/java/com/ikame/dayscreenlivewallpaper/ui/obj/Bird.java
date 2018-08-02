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

import java.util.Random;

/**
 * Created by MyPC on 01/05/2016.
 */
public class Bird {

    private Array<TextureAtlas.AtlasRegion> birds;
    private Animation birdAnimation;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private int type;
    private int state;
    private boolean front;

    private Random r = new Random();
    private float runTime;

    public Bird(float x, float y, int width, int height, int type) {
        if (type == Constant.LEFT) {
            birds = Assets.instance.assetBird.birds1;
        } else {
            birds = Assets.instance.assetBird.birds2;
        }
        updateAnimation();
        state = Constant.RUN;
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(120, 30);
        acceleration = new Vector2(100, 30);
        this.type = type;
        rotation = 0f;
        front = false;
        runTime = new Random().nextFloat();
    }

    public void updateAnimation() {
        birdAnimation = new Animation(0.04f, birds);
        birdAnimation.setPlayMode(Animation.LOOP);

    }

    public void draw(SpriteBatch batch, int pos) {
        runTime += Gdx.graphics.getDeltaTime();
        update();
        batch.draw(birdAnimation.getKeyFrame(runTime), position.x + pos, position.y, width / 2, height / 2, width, height, 1, 1, rotation);
    }

    public void update() {
        switch (state) {
            case Constant.RUN:
                run();
                Log.e("Run", "Oke");
                break;
            case Constant.TURN:
                turn();
                Log.e("Turn", "Oke");
                break;
            case Constant.SLOW:
                slow();
                Log.e("Slow", "Oke");
                break;
            case Constant.FAST:
                fast();
                Log.e("Fast", "Oke");
                break;
            default:
                break;
        }
        render();
    }

    public void isFast() {
        state = Constant.FAST;
        velocity.x = 300;
        int i = r.nextInt(2);
        if (i == 0) {
            velocity.y = 200;
        } else {
            velocity.y = -200;
        }
    }

    public boolean isWall() {
        if (type == Constant.LEFT) {
            if (position.x > Constant.WIDTH * 2 + 1000) {
                return true;
            }
        } else {
            if (position.x < -1000) {
                return true;
            }
        }
        return false;
    }

    public void isTurn() {
        state = Constant.TURN;
        velocity.x = 300;
        int i = r.nextInt(2);
        if (i == 0) {
            velocity.y = 200;
        } else {
            velocity.y = -200;
        }
        if (type == Constant.LEFT) {
            birds = Assets.instance.assetBird.birds2;
        } else {
            birds = Assets.instance.assetBird.birds1;
        }
        updateAnimation();
        type *= -1;
    }

    public void fast() {
        acceleration.x = 300;
        velocity.add(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (velocity.x >= 400) {
            state = Constant.SLOW;
        }
    }

    public void slow() {
        velocity.sub(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (velocity.x <= 120) {
            velocity.x = 120;
            velocity.y = 30;
            acceleration.x = 100;
            state = Constant.RUN;
        }
    }

    public void turn() {
        acceleration.x = 400;
        velocity.add(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (velocity.x >= 400) {
            state = Constant.SLOW;
        }
    }

    public void run() {
        velocity.add(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (velocity.x > 200) {
            velocity.x = 120;
            int i = r.nextInt(2);
            if (i == 0) {
                velocity.y = 30;
            } else {
                velocity.y = -30;
            }
        }
    }

    public void render() {
        if (type == Constant.LEFT) {
            position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
        } else {
            position.sub(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
        }
    }

    public boolean isTouch(int screenX, int screenY) {
        if (screenX > position.x - Constant.RADIUS && screenX < position.x + width + Constant.RADIUS
                && screenY > position.y - Constant.RADIUS && screenY < position.y + height + Constant.RADIUS) {
            return true;
        }
        return false;
    }

    public boolean isTouch2Turn(int screenX, int screenY) {
        int mX = (int) ((2 * position.x + width) / 2);
        if (type == Constant.LEFT && screenX >= mX) {
            return true;
        }
        if (type == Constant.RIGHT && screenX <= mX) {
            return true;
        }
        return false;
    }

    public boolean isFront() {
        return front;
    }

    public void setFront(boolean front) {
        this.front = front;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}

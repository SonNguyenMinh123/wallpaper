package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 10/05/2016.
 */
public class Birds {

    private Context context;
    private SharedPreferences prefs;
    private boolean power;
    private List<Bird> birds;
    private boolean group;
    private int type;

    public Birds(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        power = prefs.getBoolean(Constant.VOLUME, true);
        initBird();
    }

    public void initBird() {
        int tmp = new Random().nextInt(2);
        if (tmp == 0) {
            this.type = Constant.LEFT;
        } else {
            this.type = Constant.RIGHT;
        }
        group = true;
        Random r = new Random();
        int mX = 0;
        if (type == Constant.LEFT) {
            mX = 0;
        } else {
            mX = Constant.WIDTH * 2;
        }
        birds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mX += i * type * 40;
            Bird bird = new Bird(mX, r.nextInt(100) + 400, Constant.BIRD_WIDTH, Constant.BIRD_HEIGHT, type);
            birds.add(bird);
        }
    }


    public void draw(SpriteBatch batch, int pos) {
        for (Bird bird : birds) {
            bird.draw(batch, pos);
        }
        if (isWall()) {
            initBird();
        }
    }

    public boolean touchInGroup(int screenX, int screenY) {
        for (Bird bird : birds) {
            if (bird.isTouch(screenX, screenY)) {
                return true;
            }
        }
        return false;
    }

    public void touch(int screenX, int screenY) {
        if (group) {
            if (touchInGroup(screenX, screenY)) {
                power = prefs.getBoolean(Constant.VOLUME, true);
                if (power) {
                    Assets.flap.play();
                }
                for (Bird bird : birds) {
                    if (type == Constant.LEFT) {
                        if (bird.getPosition().x > screenX) {
                            bird.isFast();
                        } else {
                            bird.isTurn();
                        }
                    } else {
                        if (bird.getPosition().x > screenX) {
                            bird.isTurn();
                        } else {
                            bird.isFast();
                        }
                    }
                }
            }
            group = false;
        } else {
            touchBird(screenX, screenY);
        }
    }

    public boolean isWall() {
        for (Bird bird : birds) {
            if (bird.isWall() == false) {
                return false;
            }
        }
        return true;
    }

    public void touchBird(int screenX, int screenY) {
        for (Bird bird : birds) {
            if (bird.isTouch(screenX, screenY)) {
                power = prefs.getBoolean(Constant.VOLUME, true);
                if (power) {
                    Assets.flap.play();
                }
                if (bird.isTouch2Turn(screenX, screenY)) {
                    bird.isTurn();
                } else {
                    bird.isFast();
                }
            }
        }
    }
}

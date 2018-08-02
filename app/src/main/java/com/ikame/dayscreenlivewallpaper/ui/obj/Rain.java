package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 21/05/2016.
 */
public class Rain {

    private Context context;
    private SharedPreferences prefs;
    private List<SmallRain> list;
    private boolean draw;

    public Rain(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            SmallRain smallRain = new SmallRain();
            list.add(smallRain);
        }
        update();
    }

    public void update() {
        int condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        if (condition == Constant.RAIN) {
            draw = true;
        } else {
            draw = false;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        update();
        if (draw) {
            for (SmallRain smallRain : list) {
                smallRain.draw(batch, mPos);
            }
        }
    }


    public class SmallRain {
        private TextureRegion rain;
        private Vector2 pos, size, vel, acc;
        private Random r = new Random();

        public SmallRain() {
            rain = Assets.assetRain.rain3;
            pos = new Vector2(r.nextInt(Constant.WIDTH * 2), r.nextInt(Constant.HEIGHT));
            size = new Vector2(rain.getRegionWidth() * (r.nextInt(2) + 1), rain.getRegionHeight() * (r.nextInt(2) + 1));
//            size = new Vector2(rain.getRegionWidth() / (float) 10, rain.getRegionHeight() / (float) 10);
            vel = new Vector2(0, r.nextInt(100) + 400);
            acc = new Vector2(0, r.nextInt(100) + 500);
        }

        public void reset() {
            pos = new Vector2(r.nextInt(Constant.WIDTH * 2), r.nextInt(Constant.HEIGHT));
            size = new Vector2(rain.getRegionWidth() * (r.nextInt(2) + 1), rain.getRegionHeight() * (r.nextInt(2) + 1));
//            size = new Vector2(rain.getRegionWidth() / (float) 3, rain.getRegionHeight() / (float) 3);
            vel = new Vector2(0, r.nextInt(100) + 400);
            acc = new Vector2(0, r.nextInt(100) + 500);
        }

        public boolean check() {
            if (pos.y > Constant.HEIGHT) {
                return true;
            }
            return false;
        }

        public void draw(SpriteBatch batch, int mPos) {
            if (check()) {
                reset();
            }
            vel.add(acc.cpy().scl(Gdx.graphics.getDeltaTime()));
            pos.add(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
            batch.draw(rain, pos.x + mPos, pos.y, size.x, size.y);
        }
    }
}

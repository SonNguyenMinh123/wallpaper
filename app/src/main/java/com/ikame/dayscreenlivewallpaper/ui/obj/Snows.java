package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 23/05/2016.
 */
public class Snows {

    private boolean draw;
    private Context context;
    private SharedPreferences prefs;
    private List<Snow> list;

    public Snows(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            Snow snow = new Snow();
            list.add(snow);
        }
        update();
    }

    public void update() {
        int condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        if (condition == Constant.SNOW) {
            draw = true;
        } else {
            draw = false;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        update();
        if (draw) {
            for (Snow snow : list) {
                snow.draw(batch, mPos);
            }
        }
    }

    public class Snow {
        private TextureRegion snow;
        private Vector2 pos, size, vel, acc;
        private Random r = new Random();
        private float rot;
        private int speed;

        public Snow() {
            Texture texture = new Texture(Gdx.files.internal("snow/snow.png"));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            snow = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
            snow.flip(false, true);
            reset();
        }

        public void reset() {
            pos = new Vector2(r.nextInt(Constant.WIDTH * 2), r.nextInt(Constant.HEIGHT));
            size = new Vector2(snow.getRegionWidth() * 2, snow.getRegionHeight() * 2);
            vel = new Vector2(getRandom(-30, 30), getRandom(100, 200));
            rot = 0f;
            speed = getRandom(100, 200);
        }

        public boolean check() {
            if (pos.y > Constant.HEIGHT) {
                return true;
            }
            return false;
        }

        public void draw(SpriteBatch batch, int mPos) {
            pos.add(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
            rot = (speed * Gdx.graphics.getDeltaTime()) % 360;
            batch.draw(snow, pos.x + mPos, pos.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rot);
            if (check()) {
                reset();
            }
        }

        public int getRandom(int a, int b) {
            return new Random().nextInt(b - a) + a;
        }

    }


}

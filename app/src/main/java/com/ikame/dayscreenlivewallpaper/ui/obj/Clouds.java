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
public class Clouds {

    private Context context;
    private SharedPreferences prefs;
    private List<Cloud> list;
    private boolean draw;

    public Clouds(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        init();
        update();
    }

    public void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Cloud cloud = new Cloud();
            list.add(cloud);
        }
    }

    public void update() {
        int time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
        int condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        if (time == Constant.NIGHT) {
            for (Cloud cloud : list) {
                cloud.cloud = Assets.assetCloud.cloud2;
            }
        } else {
            for (Cloud cloud : list) {
                cloud.cloud = Assets.assetCloud.cloud1;
            }
        }
        if (condition == Constant.RAIN || condition == Constant.SNOW) {
            draw = false;
        } else {
            draw = true;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        update();
        if (draw) {
            for (Cloud cloud : list) {
                cloud.draw(batch, mPos);
            }
        }
    }

    public class Cloud {
        private int time, condition;
        private TextureRegion cloud;
        private Vector2 pos, size, vel;
        private boolean draw;

        public Cloud() {
            cloud = Assets.assetCloud.cloud2;
            pos = new Vector2(new Random().nextInt(Constant.WIDTH * 2), getRandom(0, Constant.HEIGHT / 3));
            size = new Vector2(cloud.getRegionWidth(), cloud.getRegionHeight());
            vel = new Vector2(20, 0);
            draw = true;
        }

        public void reset() {
            pos = new Vector2(-size.x, getRandom(0, Constant.HEIGHT / 3));
            size = new Vector2(cloud.getRegionWidth(), cloud.getRegionHeight());
            vel.x = getRandom(15, 25);
        }

        public boolean check() {
            if (pos.x > Constant.WIDTH * 2) {
                return true;
            }
            return false;
        }

        public void draw(SpriteBatch batch, int mPos) {
            if (check()) {
                reset();
            }
            pos.add(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
            batch.draw(cloud, pos.x + mPos, pos.y, size.x, size.y);
        }

        public int getRandom(int a, int b) {
            return new Random().nextInt(b - a) + a;
        }
    }
}

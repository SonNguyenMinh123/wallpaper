package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 20/05/2016.
 */
public class Sun {

    private Context context;
    private SharedPreferences prefs;
    private TextureRegion sun;
    private Vector2 pos, size;
    private boolean draw;

    public Sun(Context context) {
        this.context = context;
        sun = Assets.assetSun.sun;
        pos = new Vector2(Constant.WIDTH / 2, Constant.HEIGHT / 17);
        size = new Vector2(Constant.WIDTH / 2.5f, Constant.WIDTH / 2.5f);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        update();
    }

    public void draw(SpriteBatch batch, int mPos) {
        if (draw) {
            batch.draw(sun, pos.x + mPos / 2, pos.y, size.x, size.y);
            update();
        }
    }

    public void update() {
        int time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
        int condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        if (time == Constant.NIGHT) {
            draw = true;
            sun = Assets.assetMoon.moon1;
            pos = new Vector2(Constant.WIDTH / 2, Constant.HEIGHT / 17);
        }
        if (time == Constant.SUNRISE) {
            draw = true;
            sun = Assets.assetSun.sun;
            pos = new Vector2(Constant.WIDTH / 5, Constant.HEIGHT / 3);
        }
        if (time == Constant.DAY) {
            draw = true;
            sun = Assets.assetSun.sun;
            pos = new Vector2(Constant.WIDTH / 2, Constant.HEIGHT / 17);
        }
        if (time == Constant.SUNSET) {
            draw = true;
            sun = Assets.assetSun.sun;
            pos = new Vector2(Constant.WIDTH, Constant.HEIGHT / 5);
        }

        if (condition == Constant.RAIN || condition == Constant.SNOW) {
            draw = false;
        }
        if (condition == Constant.CLOUDS || condition == Constant.CLEAR) {
            draw = true;
        }
    }
}

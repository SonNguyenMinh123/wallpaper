package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 18/05/2016.
 */
public class Sky {
    private Context context;
    private TextureRegion sky;
    private Vector2 position;
    private int width, height;
    private SharedPreferences prefs;
    private List<TextureRegion> listNight;
    private List<TextureRegion> listDay;
    private List<TextureRegion> listSunnet;

    public Sky(Context context) {
        this.context = context;
        this.width = Constant.WIDTH * 2;
        this.height = Constant.HEIGHT;
        this.position = new Vector2(0, 0);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        init();
        update();
    }

    public void init() {
        listNight = Assets.assetSky.listNight;
        listDay = Assets.assetSky.listDay;
        listSunnet = Assets.assetSky.listSunnet;
    }

    public void update() {
        int time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
        int condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        switch (time) {
            case Constant.SUNRISE:
                sky = Assets.assetSky.sunrise;
                Log.e("SKY_SUNRISE", Assets.assetSky.sunrise.getTexture().toString());
                break;
            case Constant.DAY:
                sky = getRandom(listDay);
                Log.e("SKY_DAY", getRandom(listDay).getTexture().toString());
                break;
            case Constant.SUNSET:
                sky = getRandom(listSunnet);
                Log.e("SKY_SUNSET", Assets.assetSky.sunset.getTexture().toString());
                break;
            case Constant.NIGHT:
                sky = getRandom(listNight);
                Log.e("SKY_NIGHT", getRandom(listNight).getTexture().toString());
                break;
            default:
                break;
        }
        if (condition == Constant.RAIN) {
            if (time != Constant.NIGHT)
                sky = Assets.assetSky.rain;
        }
        if (condition == Constant.SNOW) {
            if (time != Constant.NIGHT)
                sky = Assets.assetSky.snow;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        batch.draw(sky, position.x + mPos / 2, position.y, width, height);
        Log.e("SKY", sky.getTexture().toString());

    }

    public TextureRegion getRandom(List<TextureRegion> list) {
        TextureRegion region = list.get(new Random().nextInt(list.size()));
        return region;
    }
}

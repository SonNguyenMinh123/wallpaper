package com.ikame.dayscreenlivewallpaper.common;

import com.badlogic.gdx.Gdx;

/**
 * Created by MyPC on 30/04/2016.
 */
public class Constant {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    public static final int TREE_WIDTH = (int) (Constant.WIDTH / 2.5f);
    public static final int TREE_WIDTH2 = (int) (Constant.WIDTH / 5f);
    public static final int TREE_RADIUS = 0;

    public static final String TIME_OF_DAY = "time_of_day";
    public static final int NIGHT = 0;
    public static final int SUNRISE = 1;
    public static final int DAY = 2;
    public static final int SUNSET = 4;

    public static final String WEATHER = "weather";
    public static final String CONDITIONS = "conditions";
    public static final int RAIN = 1;
    public static final int SNOW = 2;
    public static final int CLOUDS = 3;
    public static final int CLEAR = 4;

    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int BIRD_WIDTH = WIDTH / 14;
    public static final int BIRD_HEIGHT = BIRD_WIDTH;
    public static final int RABBIT_WIDTH = WIDTH / 7;
    public static final int RUN = 1;
    public static final int FAST = 0;
    public static final int SLOW = 2;
    public static final int TURN = -1;
    public static final int RADIUS = 100;

    public static final int GRASS_HEIGHT = (int) (HEIGHT / 3);
    public static final int GRASS_Y = HEIGHT - GRASS_HEIGHT;

    public static final int MOUNTAIN_HEIGHT = (int) (Constant.HEIGHT / 2.5f);
    public static final int MOUNTAIN_Y = (HEIGHT - MOUNTAIN_HEIGHT) / 2;

    public static final int RABBIT_RUN = 1;
    public static final int RABBIT_TURN = 0;
    public static final int RABBIT_LEFT = 1;
    public static final int RABBIT_RIGHT = -1;
    public static final String CITY = "city";

    public static final String VOLUME = "volume";

    public static final String SHOW = "show";

    public static final String TEMP = "tem";
    public static final String C = "°C";
    public static final String F = "°F";

    public static final String RANDOM = "random";

}

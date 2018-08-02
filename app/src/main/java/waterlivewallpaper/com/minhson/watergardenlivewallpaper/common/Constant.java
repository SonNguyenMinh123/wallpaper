package waterlivewallpaper.com.minhson.watergardenlivewallpaper.common;

import com.badlogic.gdx.Gdx;

/**
 * Created by MyPC on 30/04/2016.
 */
public class Constant {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 0;
    public static final int DOWN = 1;

    public static final String RANDOM = "random";

    public static final int RUN = 1;
    public static final int PRETURN = 2;
    public static final int TURN = 3;
    public static final int FAST = 4;
    public static final int PREFAST = 5;
    public static final int SLOW = 7;
    public static final int ACCELERATION = 600;

    public static final String TOTAL_FISH1 = "totalFish1";
    public static final String TOTAL_FISH2 = "totalFish2";
    public static final String TOTAL_FISH3 = "totalFish3";
    public static final String TOTAL_FISH4 = "totalFish4";
    public static final String TOTAL_FISH5 = "totalFish5";

    public static final int NIGHT = 0;
    public static final int SUNRISE = 1;
    public static final int DAY = 2;
    public static final int SUNSET = 4;

    public static final String TIME_OF_DAY = "time_of_day";

}

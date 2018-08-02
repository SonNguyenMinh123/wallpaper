package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Assets;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 18/05/2016.
 */
public class Sky {
    private Context context;
    private TextureRegion sky;
    private Vector2 position;
    private int width, height;
    private List<TextureRegion> listNight;
    private List<TextureRegion> listDay;
    private SharedPreferences prefs;

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

    }

    public void update() {
        int time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
        switch (time) {
            case Constant.NIGHT:
                sky = getRandom(listNight);
                break;
            case Constant.SUNRISE:
                sky = Assets.assetSky.night7;
                break;
            case Constant.DAY:
                sky = getRandom(listDay);
                break;
            case Constant.SUNSET:
                sky = Assets.assetSky.night8;
                break;
            default:
                break;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        batch.draw(sky, position.x + mPos / 2, position.y, width, height);
    }

    public TextureRegion getRandom(List<TextureRegion> list) {
        TextureRegion region = list.get(new Random().nextInt(list.size()));
        return region;
    }
}

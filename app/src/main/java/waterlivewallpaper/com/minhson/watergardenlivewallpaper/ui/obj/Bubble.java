package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Assets;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Constant;

/**
 * Created by Administrator on 24/10/2017.
 */

public class Bubble {
    private Context context;
    private SharedPreferences prefs;
    private List<SmallRain> list;
    private boolean draw;

    public Bubble(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        list = new ArrayList<>();
       /* for (int i = 0; i < 400; i++) {
            SmallRain smallRain = new SmallRain();
            list.add(smallRain);
        }*/
        for (int i = 0; i < 20; i++) {
            SmallRain smallRain = new SmallRain();
            list.add(smallRain);
        }
        update();
    }

    public void update() {
        draw = true;
    }

    public void draw(SpriteBatch batch, int mPos) {
        update();
        if (draw) {
            for (SmallRain smallRain : list) {
                smallRain.draw(batch, mPos);
            }
        }
        Log.e("bubble", "hehe2");
    }

    public TextureRegion getRandom(List<TextureRegion> list) {
        TextureRegion region = list.get(new Random().nextInt(list.size()));
        return region;
    }

    public class SmallRain {
        private TextureRegion sky;
        private int i;
        private Vector2 pos, size, vel, acc;
        private Random random = new Random();
        private List<TextureRegion> listSky;

        public SmallRain() {
            listSky = Assets.assetRain.list;
            sky = getRandom(listSky);

            pos = new Vector2(random.nextInt(Constant.HEIGHT * 2), random.nextInt(Constant.WIDTH));
            size = new Vector2(sky.getRegionWidth() * (random.nextInt(1) + 1) - 20, sky.getRegionHeight() * (random.nextInt(1) + 1) - 20);
            vel = new Vector2(0, random.nextInt(10) + 100);
            acc = new Vector2(0, random.nextInt(10) + 100);

        }

        public void reset() {
            pos = new Vector2(random.nextInt(Constant.HEIGHT * 2), Constant.HEIGHT);
            size = new Vector2(sky.getRegionWidth() * (random.nextInt(1) + 1) - 20, sky.getRegionHeight() * (random.nextInt(1) + 1) - 20);
            vel = new Vector2(0, random.nextInt(30) + random.nextInt(50) + 10);
            acc = new Vector2(0, random.nextInt(10) + 50);

        }

        public boolean check() {
            if (pos.y < 0) {
                return true;
            }
            return false;
        }

        public void draw(SpriteBatch batch, int mPos) {
            if (check()) {
                reset();
            }
            vel.add(acc.cpy().scl(Gdx.graphics.getDeltaTime()));
            pos.sub(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
            batch.draw(sky, pos.x + mPos, pos.y, size.x, size.y);
            Log.e("bubble", "hehe");
        }
    }
}

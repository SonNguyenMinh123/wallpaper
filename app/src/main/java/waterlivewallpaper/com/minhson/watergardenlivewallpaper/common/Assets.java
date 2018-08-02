package waterlivewallpaper.com.minhson.watergardenlivewallpaper.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 23/10/2017.
 */

public class Assets implements Disposable, AssetErrorListener {
    public static final Assets instance = new Assets();
    public static final String TAG = "hehe";
    public static AssetFish1 assetFish1;
    public static AssetFish2 assetFish2;
    public static AssetFish3 assetFish3;
    public static AssetFish4 assetFish4;
    public static AssetFish5 assetFish5;
    public static AssetSky assetSky;
    public static AssetBubble assetRain;
    private static AssetManager assetManager;

    private Assets() {

    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load("fish1/fish1.atlas", TextureAtlas.class);
        assetManager.load("fish2/fish2.atlas", TextureAtlas.class);
        assetManager.load("fish3/fish3.atlas", TextureAtlas.class);
        assetManager.load("fish4/fish4.atlas", TextureAtlas.class);
        assetManager.load("fish5/fish5.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas fish1 = assetManager.get("fish1/fish1.atlas");
        TextureAtlas fish2 = assetManager.get("fish2/fish2.atlas");
        TextureAtlas fish3 = assetManager.get("fish3/fish3.atlas");
        TextureAtlas fish4 = assetManager.get("fish4/fish4.atlas");
        TextureAtlas fish5 = assetManager.get("fish5/fish5.atlas");

        for (Texture t : fish1.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : fish2.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : fish3.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : fish4.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : fish5.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        assetFish1 = new AssetFish1(fish1);
        assetFish2 = new AssetFish2(fish2);
        assetFish3 = new AssetFish3(fish3);
        assetFish4 = new AssetFish4(fish4);
        assetFish5 = new AssetFish5(fish5);
        assetSky = new AssetSky();
        assetRain = new AssetBubble();

    }

    @Override
    public void error(String s, Class aClass, Throwable throwable) {
        Gdx.app.error(TAG, "Khong the load '" + s + "'", throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public TextureRegion getRegion(String name, boolean x, boolean y) {
        Texture texture = new Texture(Gdx.files.internal(name));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        region.flip(x, y);
        return region;
    }

    public class AssetFish1 {
        public final Array<TextureAtlas.AtlasRegion> list;

        public AssetFish1(TextureAtlas atlas) {
            list = atlas.findRegions("fishanim");
            for (TextureAtlas.AtlasRegion atlasRegion : list) {
                atlasRegion.flip(false, true);
            }
        }
    }

    public class AssetFish2 {
        public final Array<TextureAtlas.AtlasRegion> list;

        public AssetFish2(TextureAtlas atlas) {
            list = atlas.findRegions("fishanim");
            for (TextureAtlas.AtlasRegion atlasRegion : list) {
                atlasRegion.flip(false, true);
            }
        }
    }

    public class AssetFish3 {
        public final Array<TextureAtlas.AtlasRegion> list;

        public AssetFish3(TextureAtlas atlas) {
            list = atlas.findRegions("fishanim");
            for (TextureAtlas.AtlasRegion atlasRegion : list) {
                atlasRegion.flip(false, true);
            }
        }
    }

    public class AssetFish4 {
        public final Array<TextureAtlas.AtlasRegion> list;

        public AssetFish4(TextureAtlas atlas) {
            list = atlas.findRegions("fishanim");
            for (TextureAtlas.AtlasRegion atlasRegion : list) {
                atlasRegion.flip(false, true);
            }
        }
    }

    public class AssetFish5 {
        public final Array<TextureAtlas.AtlasRegion> list;

        public AssetFish5(TextureAtlas atlas) {
            list = atlas.findRegions("fishanim");
            for (TextureAtlas.AtlasRegion atlasRegion : list) {
                atlasRegion.flip(false, true);
            }
        }
    }

    public class AssetSky {
        public final TextureRegion night1;
        public final TextureRegion night2;
        public final TextureRegion night3;
        public final TextureRegion night4;
        public final TextureRegion night5;
        public final TextureRegion night6;
        public final TextureRegion night7;
        public final TextureRegion night8;
        public final TextureRegion night9;
        public final List<TextureRegion> listNight;
        public final List<TextureRegion> listDay;

        public AssetSky() {
            listNight = new ArrayList<>();
            night1 = getRegion("background/bg9.jpg", false, true);
            night2 = getRegion("background/bg3.jpg", false, true);
            night3 = getRegion("background/bg_day.jpg", false, true);
            listNight.add(night1);
            listNight.add(night2);
            listNight.add(night3);

            listDay = new ArrayList<>();
            night4 = getRegion("background/bg_night.jpg", false, true);
            night5 = getRegion("background/bg5.jpg", false, true);
            night6 = getRegion("background/bg6.jpg", false, true);
            listDay.add(night4);
            listDay.add(night5);
            listDay.add(night6);

            night7 = getRegion("background/bg7.jpg", false, true);
            night8 = getRegion("background/bg8.jpg", false, true);
            night9 = getRegion("background/bg4.jpg", false, true);

        }
    }

    public class AssetBubble {
        public final TextureRegion rain1;
        public final TextureRegion rain2;
        public final TextureRegion rain3;
        public final TextureRegion rain4;
        public final List<TextureRegion> list;

        public AssetBubble() {
            rain1 = getRegion("bubble/bubble1.png", false, true);
            rain2 = getRegion("bubble/bubble2.png", false, true);
            rain3 = getRegion("bubble/bubble3.png", false, true);
            rain4 = getRegion("bubble/bubble4.png", false, true);

            list = new ArrayList<>();
            list.add(rain1);
            list.add(rain2);
            list.add(rain3);
            list.add(rain4);
        }
    }
}

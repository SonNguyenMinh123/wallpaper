package com.ikame.dayscreenlivewallpaper.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 28/04/2016.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = "hehe";
    public static final Assets instance = new Assets();
    public static AssetManager assetManager;
    public static AssetMountain assetMountain;
    public static AssetSky assetSky;
    public static AssetBird assetBird;
    public static AssetRabbit assetRabbit;
    public static AssetTree assetTree;
    public static AssetTree2 assetTree2;
    public static AssetLeaf assetLeaf;
    public static AssetSun assetSun;
    public static AssetRain assetRain;
    public static AssetCloud assetCloud;
    public static AssetMoon assetMoon;
    public static AssetIcon assetIcon;
    public static Sound flap;
    public static BitmapFont font;

    private Assets() {

    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load("bird/silhouette_2.atlas", TextureAtlas.class);
        assetManager.load("rabbit/rabbit.atlas", TextureAtlas.class);
        assetManager.load("tree/tree.atlas", TextureAtlas.class);
        assetManager.load("tree2/skeleton.atlas", TextureAtlas.class);
        assetManager.load("cloud/cloud0.atlas", TextureAtlas.class);
        assetManager.load("moon/moon.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get("bird/silhouette_2.atlas");
        TextureAtlas atlas1 = assetManager.get("rabbit/rabbit.atlas");
        TextureAtlas atlas2 = assetManager.get("tree/tree.atlas");
        TextureAtlas atlas5 = assetManager.get("tree2/skeleton.atlas");

        TextureAtlas atlas3 = assetManager.get("cloud/cloud0.atlas");
        TextureAtlas atlas4 = assetManager.get("moon/moon.atlas");

        for (Texture t : atlas.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : atlas1.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : atlas2.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : atlas5.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : atlas3.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        for (Texture t : atlas4.getTextures())
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        assetMountain = new AssetMountain();
        assetSky = new AssetSky();
        assetBird = new AssetBird(atlas);
        assetRabbit = new AssetRabbit(atlas1);
        assetTree = new AssetTree(atlas2);
        assetTree2 = new AssetTree2(atlas5);
        assetLeaf = new AssetLeaf();
        assetSun = new AssetSun();
        assetRain = new AssetRain();
        assetCloud = new AssetCloud(atlas3);
        assetMoon = new AssetMoon(atlas4);
        assetIcon = new AssetIcon();
        flap = Gdx.audio.newSound(Gdx.files.internal("single_bird.mp3"));
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
        font.setScale(2f, -2f);
    }

    public class AssetMoon {
        public final TextureAtlas.AtlasRegion moon1;
        public final TextureAtlas.AtlasRegion moon2;


        public AssetMoon(TextureAtlas atlas) {
            moon1 = atlas.findRegion("moon1");
            moon1.flip(false, true);
            moon2 = atlas.findRegion("moon2");
            moon2.flip(false, true);
        }
    }

    public class AssetIcon {

        public final TextureRegion icon1;
        public final TextureRegion icon2;
        public final TextureRegion icon3;
        public final TextureRegion icon4;
        public final TextureRegion icon5;

        public AssetIcon() {
            icon1 = getRegion("icon/02d.png", false, true);
            icon2 = getRegion("icon/02n.png", false, true);
            icon3 = getRegion("icon/03n.png", false, true);
            icon4 = getRegion("icon/09n.png", false, true);
            icon5 = getRegion("icon/13n.png", false, true);
        }
    }

    public class AssetTree {
        public final TextureAtlas.AtlasRegion L26;
        public final TextureAtlas.AtlasRegion L31;
        public final TextureAtlas.AtlasRegion L32;
        public final TextureAtlas.AtlasRegion L34;
        public final TextureAtlas.AtlasRegion L36;
        public final TextureAtlas.AtlasRegion L37;
        public final TextureAtlas.AtlasRegion L39;

        public AssetTree(TextureAtlas atlas) {
            L26 = atlas.findRegion("Layer 26");
            L26.flip(false, true);
            L31 = atlas.findRegion("Layer 31");
            L31.flip(false, true);
            L32 = atlas.findRegion("Layer 32");
            L32.flip(false, true);
            L34 = atlas.findRegion("Layer 34");
            L34.flip(false, true);
            L36 = atlas.findRegion("Layer 36");
            L36.flip(false, true);
            L37 = atlas.findRegion("Layer 37");
            L37.flip(false, true);
            L39 = atlas.findRegion("Layer 39");
            L39.flip(false, true);
        }
    }

    public class AssetTree2 {
        public final TextureAtlas.AtlasRegion L26_2;
        public final TextureAtlas.AtlasRegion L31_2;
        public final TextureAtlas.AtlasRegion L32_2;
        public final TextureAtlas.AtlasRegion L34_2;
        public final TextureAtlas.AtlasRegion L36_2;
        public final TextureAtlas.AtlasRegion l_branch;
        public final TextureAtlas.AtlasRegion leaves3Region;
        public final TextureAtlas.AtlasRegion L39_2;

        public AssetTree2(TextureAtlas atlas) {
            L26_2 = atlas.findRegion("l_branch");
            L26_2.flip(false, true);
            L31_2 = atlas.findRegion("l_h_leaves");
            L31_2.flip(false, true);
            L32_2 = atlas.findRegion("l_leaves");
            L32_2.flip(false, true);
            L34_2 = atlas.findRegion("l_m_branch");
            L34_2.flip(false, true);
            L36_2 = atlas.findRegion("l_m_leaves1");
            L36_2.flip(false, true);

            // fix
            l_branch = atlas.findRegion("l_branch");
            l_branch.flip(false, true);

            leaves3Region=atlas.findRegion("l_branch");
            leaves3Region.flip(false, false);

            L39_2 = atlas.findRegion("root");
            L39_2.flip(false, true);
        }
    }

    public class AssetMountain {
        public final TextureRegion mountain1;

        public AssetMountain() {
            mountain1 = getRegion("mountain/mountain.png", false, true);
        }
    }

    public class AssetCloud {
        public final TextureAtlas.AtlasRegion cloud1;
        public final TextureAtlas.AtlasRegion cloud2;

        public AssetCloud(TextureAtlas atlas) {
            cloud1 = atlas.findRegion("cloud_behind");
            cloud1.flip(false, true);
            cloud2 = atlas.findRegion("cloud_front");
            cloud2.flip(false, true);
        }
    }

    public class AssetSun {
        public final TextureRegion sun;

        public AssetSun() {
            sun = getRegion("sun/sun2.png", false, true);
        }
    }

    public class AssetLeaf {
        public final TextureRegion leaf1;
        public final TextureRegion leaf2;
        public final TextureRegion leaf3;
        public final List<TextureRegion> list;

        public AssetLeaf() {
            leaf1 = getRegion("leaf/leaf1.png", false, true);
            leaf2 = getRegion("leaf/leaf2.png", false, true);
            leaf3 = getRegion("leaf/leaf3.png", false, true);
            list = new ArrayList<>();
            list.add(leaf1);
            list.add(leaf2);
            list.add(leaf3);
        }
    }

    public class AssetRain {
        public final TextureRegion rain1;
        public final TextureRegion rain2;
        public final TextureRegion rain3;

        public AssetRain() {
            rain1 = getRegion("rain/rain01.png", false, true);
            rain2 = getRegion("rain/rain02.png", false, true);
            rain3 = getRegion("rain/rain03.png", false, true);
        }
    }

    public class AssetSky {
        public final TextureRegion night1;
        public final TextureRegion night2;
        public final TextureRegion night3;
        public final List<TextureRegion> listNight;
        public final List<TextureRegion> listDay;
        public final List<TextureRegion> listSunnet;
        public final TextureRegion day1;
        public final TextureRegion day2;
        public final TextureRegion day3;

        public final TextureRegion sunrise;
        public final TextureRegion snow;
        public final TextureRegion sunset;
        public final TextureRegion rain;

        public AssetSky() {
            listNight = new ArrayList<>();
            night1 = getRegion("sky/night1.jpg", false, true);
            night2 = getRegion("sky/night2.jpg", false, true);
            night3 = getRegion("sky/night3.jpg", false, true);
            listNight.add(night1);
            listNight.add(night2);
            listNight.add(night3);

            listDay = new ArrayList<>();
            day1 = getRegion("sky/day.jpg", false, true);
            day2 = getRegion("sky/day1.jpg", false, true);
            day3 = getRegion("sky/day2.jpg", false, true);
            listDay.add(day1);
            listDay.add(day2);
            listDay.add(day3);

            listSunnet = new ArrayList<>();
            sunrise = getRegion("sky/sunrise.jpg", false, true);
            sunset = getRegion("sky/sunset.jpg", false, true);
            listSunnet.add(sunrise);
            listSunnet.add(sunrise);

            rain = getRegion("sky/rain.jpg", false, true);
            snow = getRegion("sky/snow.jpg", false, true);

        }
    }

    public class AssetRabbit {
        public final Array<TextureAtlas.AtlasRegion> rabbit1;
        public final Array<TextureAtlas.AtlasRegion> rabbit2;

        public AssetRabbit(TextureAtlas atlas) {
            rabbit1 = atlas.findRegions("img");
            rabbit2 = atlas.findRegions("img");
            for (TextureAtlas.AtlasRegion atlasRegion : rabbit1) {
                atlasRegion.flip(false, true);
            }
            for (TextureAtlas.AtlasRegion atlasRegion : rabbit2) {
                atlasRegion.flip(true, true);
            }
        }
    }

    public class AssetBird {
        public final Array<TextureAtlas.AtlasRegion> birds1;
        public final Array<TextureAtlas.AtlasRegion> birds2;

        public AssetBird(TextureAtlas atlas) {
            birds1 = atlas.findRegions("bird");
            birds2 = atlas.findRegions("bird");
            for (TextureAtlas.AtlasRegion atlasRegion : birds1) {
                atlasRegion.flip(false, true);
            }
            for (TextureAtlas.AtlasRegion atlasRegion : birds2) {
                atlasRegion.flip(true, true);
            }
        }
    }

    public TextureRegion getRegion(String name, boolean x, boolean y) {
        Texture texture = new Texture(Gdx.files.internal(name));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        region.flip(x, y);
        return region;
    }

    @Override
    public void error(String s, Class aClass, Throwable throwable) {
        Gdx.app.error(TAG, "Khong the load '" + s + "'", (Exception) throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        flap.dispose();
    }
}

package com.ikame.dayscreenlivewallpaper.ui.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;
import com.ikame.dayscreenlivewallpaper.ui.obj.Birds;
import com.ikame.dayscreenlivewallpaper.ui.obj.Clouds;
import com.ikame.dayscreenlivewallpaper.ui.obj.Grass;
import com.ikame.dayscreenlivewallpaper.ui.obj.Mountain;
import com.ikame.dayscreenlivewallpaper.ui.obj.Rabbit;
import com.ikame.dayscreenlivewallpaper.ui.obj.Rain;
import com.ikame.dayscreenlivewallpaper.ui.obj.Sky;
import com.ikame.dayscreenlivewallpaper.ui.obj.Snows;
import com.ikame.dayscreenlivewallpaper.ui.obj.Sun;
import com.ikame.dayscreenlivewallpaper.ui.obj.Tree;
import com.ikame.dayscreenlivewallpaper.ui.obj.Tree2;
import com.ikame.dayscreenlivewallpaper.ui.receiver.MyReceiver;

/**
 * Created by MyPC on 30/04/2016.
 */
public class MyGame extends Game {
    private Context context;
    private MyReceiver receiver;
    private GameScreen screen;

    public MyGame(Context context) {
        this.context = context;

    }

    @Override
    public void create() {
        receiver = new MyReceiver();
        receiver.setAlarm(context);
        Texture.setEnforcePotImages(false);
        Assets.instance.init(new AssetManager());
        screen = new GameScreen();
        setScreen(screen);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }

    @Override
    public void dispose() {
        super.dispose();
        receiver.cancelAlarm(context);
        Assets.instance.dispose();
    }

    public class GameScreen extends InputAdapter implements Screen, SharedPreferences.OnSharedPreferenceChangeListener {
        private SharedPreferences prefs;
        private OrthographicCamera camera;
        private SpriteBatch batch;
        private float runTime = 0;
        private int mPos;
        private Birds birds;
        private Grass grass;
        private Mountain mountain;
        private Sky sky;
        private Rabbit rabbit;
        private Tree tree;
        private Tree2 tree2;
        private Sun sun;
        private Rain rain;
        private Clouds clouds;
        private Snows snows;
        private TextureRegion icon;

        private int time, condition;
        private float tem;
        private String city;
        private int mX;

        public GameScreen() {
            camera = new OrthographicCamera();
            camera.setToOrtho(true, Constant.WIDTH, Constant.HEIGHT);

            batch = new SpriteBatch();
            batch.setProjectionMatrix(camera.combined);
            Gdx.input.setInputProcessor(this);

            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.registerOnSharedPreferenceChangeListener(this);

            time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
            condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
            tem = prefs.getFloat(Constant.WEATHER, 25f);

            birds = new Birds(context);
            grass = new Grass();
            mountain = new Mountain();
            sky = new Sky(context);
            rabbit = new Rabbit();
            tree = new Tree(context);
            tree2 = new Tree2(context);
            sun = new Sun(context);
            rain = new Rain(context);
            clouds = new Clouds(context);
            snows = new Snows(context);
            icon = Assets.assetIcon.icon1;

            city = prefs.getString(Constant.CITY, "The World");
            tem = prefs.getFloat(Constant.WEATHER, 30);
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            mX = screenX;
            int x = screenX - mPos;
            birds.touch(x, screenY);
            tree.onTouch(x, screenY);
            tree2.onTouch(x, screenY);
            rabbit.onTouch();
            return super.touchDown(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {

            int x = screenX - mPos;
            birds.touch(x, screenY);

            int mCurrentX = screenX;
            if (mCurrentX > mX) {
                if (mPos < 0)
                    mPos += 15;
            } else {
                if (mCurrentX < mX) {
                    if (mPos > -Constant.WIDTH)
                        mPos -= 15;
                }
            }
            mX = mCurrentX;
            return super.touchDragged(screenX, screenY, pointer);
        }

        public void draw() {
            sky.draw(batch, mPos);
            sun.draw(batch, mPos);
            mountain.draw(batch, mPos);
            clouds.draw(batch, mPos);
            birds.draw(batch, mPos);
            tree.draw(batch, runTime, mPos);
            tree2.draw(batch, runTime, mPos);
            rabbit.draw(batch, runTime, mPos);
            rain.draw(batch, mPos);
            snows.draw(batch, mPos);
            grass.draw(batch, mPos);

            boolean s = prefs.getBoolean(Constant.SHOW, true);
            boolean tempa = prefs.getBoolean(Constant.TEMP, true);
            time = prefs.getInt(Constant.TIME_OF_DAY, Constant.NIGHT);
            condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
            city = prefs.getString(Constant.CITY, "The World");
            tem = prefs.getFloat(Constant.WEATHER, 30);
            if (condition == Constant.RAIN) {
                icon = Assets.assetIcon.icon4;
            }
            if (condition == Constant.CLEAR) {
                if (time == Constant.NIGHT) {
                    icon = Assets.assetIcon.icon2;
                } else {
                    icon = Assets.assetIcon.icon1;
                }
            }
            if (condition == Constant.CLOUDS) {
                icon = Assets.assetIcon.icon3;
            }
            if (condition == Constant.SNOW) {
                icon = Assets.assetIcon.icon5;
            }
            if (s) {
                Assets.font.draw(batch, city, 100, 250);
                int t = (int) ((tem * 1.8) + 32);
                String w = "";
                if (tempa) {
                    w = String.valueOf((int) tem) + "*C";
                } else {
                    w = String.valueOf((int) t) + "*F";
                }
                Assets.font.draw(batch, w, 100, 330);
                batch.draw(icon, 250, 305, icon.getRegionWidth() * 2, icon.getRegionHeight() * 2);
            }
        }

        @Override
        public void render(float v) {
            runTime += v;
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            draw();
            batch.end();
        }

        @Override
        public void resize(int i, int i1) {

        }

        @Override
        public void show() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void dispose() {
            prefs.unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals(Constant.RANDOM)) {
                sky.update();
            }
        }
    }
}

package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.game;

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

import java.util.ArrayList;
import java.util.List;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Assets;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Constant;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj.Bubble;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj.Fish;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj.Sky;

/**
 * Created by Administrator on 23/10/2017.
 */

public class MyGame extends Game {
    private Context context;
    private GameScreen screen;

    public MyGame(Context context) {
        this.context = context;

    }

    @Override
    public void create() {
        Texture.setEnforcePotImages(false);
        Assets.instance.init(new AssetManager());
        screen = new GameScreen();
        setScreen(screen);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

    }

    public class GameScreen extends InputAdapter implements Screen, SharedPreferences.OnSharedPreferenceChangeListener {
        private SharedPreferences prefs;
        private OrthographicCamera camera;
        private SpriteBatch batch;
        private float runTime = 0;
        private int mPos;
        private List<Fish> fishs;
        private int mFist1, mFist2, mFist3, mFist4, mFist5;
        private Sky sky;
        private Bubble bubble;
        private int mX;

        public GameScreen() {
            camera = new OrthographicCamera();
            camera.setToOrtho(true, Constant.WIDTH, Constant.HEIGHT);

            batch = new SpriteBatch();
            batch.setProjectionMatrix(camera.combined);
            Gdx.input.setInputProcessor(this);

            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.registerOnSharedPreferenceChangeListener(this);

            sky = new Sky(context);
            bubble = new Bubble(context);
            initFish();
        }

        private void initFish() {
            mFist1 = (int) prefs.getFloat(Constant.TOTAL_FISH1, 2f);
            mFist2 = (int) prefs.getFloat(Constant.TOTAL_FISH2, 2f);
            mFist3 = (int) prefs.getFloat(Constant.TOTAL_FISH3, 2f);
            mFist4 = (int) prefs.getFloat(Constant.TOTAL_FISH4, 2f);
            mFist5 = (int) prefs.getFloat(Constant.TOTAL_FISH5, 2f);
            fishs = new ArrayList<>();
            initList(1, mFist1);
            initList(2, mFist2);
            initList(3, mFist3);
            initList(4, mFist4);
            initList(5, mFist5);
        }

        private void initList(int type, int amount) {
            for (int i = 0; i < amount; i++) {
                Fish fish = new Fish(type);
                fishs.add(fish);
            }
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            mX = screenX;
            int x = screenX - mPos;
            for (Fish fish : fishs) {
                fish.onTouch(x, screenY);
            }
            return super.touchDown(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {

            int x = screenX - mPos;
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
            for (Fish fish : fishs) {
                fish.onTouch(x, screenY);
            }
            return super.touchDragged(screenX, screenY, pointer);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(Constant.RANDOM)) {
                sky.update();
            }
            if (key.equals(Constant.TOTAL_FISH1) || key.equals(Constant.TOTAL_FISH2) ||
                    key.equals(Constant.TOTAL_FISH3) || key.equals(Constant.TOTAL_FISH4) || key.equals(Constant.TOTAL_FISH5)) {
                initFish();
            }
        }

        private void draw() {
            sky.draw(batch, mPos);
            for (Fish fish : fishs) {
                fish.draw(batch, mPos);
            }
            bubble.draw(batch, mPos);

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
    }
}

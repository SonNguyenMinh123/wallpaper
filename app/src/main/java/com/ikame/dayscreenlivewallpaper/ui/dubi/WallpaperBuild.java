package com.ikame.dayscreenlivewallpaper.ui.dubi;

import android.util.Log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.ikame.dayscreenlivewallpaper.ui.dubi.models.BirdEntity;

/**
 * Created by Administrator on 17/10/2017.
 * thang nay la lop quan ly, tuong tac callback,...
 */

public class WallpaperBuild extends Game implements Disposable,AssetErrorListener{
    public static final String BIRD_PATH = "silhouette_2.atlas";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    public static final int BIRD_WIDTH = WIDTH / 14;
    public static final int BIRD_HEIGHT = BIRD_WIDTH;
    private static final String TAG = WallpaperBuild.class.getSimpleName();
    private AssetManager mAssetManager;
    private Array<TextureAtlas.AtlasRegion> mListBirdRegion; // danh sach cac vung cat anh tu file atlas
    private ScreenAdapter mScreenAdapter;
    private int mPos;

    @Override
    public void create() {
        mAssetManager = new AssetManager();
        initData();
        mScreenAdapter = new ScreenAdapter();
        setScreen(mScreenAdapter);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

    }

    /**
     * khoi tao, setup, config du lieu o day
     * thang nay chi config du lieu, chua add vao camera
     */
    private void initData() {
        Texture.setEnforcePotImages(false);
        // load no ra
        mAssetManager.load(BIRD_PATH, TextureAtlas.class);
        //  mAssetManager.load("fileatlat2.atlas", TextureAtlas.class);// ...

        // load xong thi finish
        mAssetManager.finishLoading();

        // ok. Da load roi, gio get no ra thoi
        TextureAtlas birdAtlas = mAssetManager.get(BIRD_PATH);
        // a, neu tim kiem region don thi nhu the, con lay danh sach ra thi them s
        mListBirdRegion = birdAtlas.findRegions("bird"); // no ten la bird nhe
        for(Texture t:birdAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        }

        for (TextureAtlas.AtlasRegion atlasRegion:mListBirdRegion) {
            atlasRegion.flip(false,true);
        }
    }

    @Override
    public void error(String s, Class aClass, Throwable throwable) {
        Log.d(TAG,"error: "+s);
    }

    /**
     * thang nay la lop xay dung camera
     * lop camera day, ti add sau
     */
    class ScreenAdapter extends InputAdapter implements Screen {

        OrthographicCamera mCamera;
        SpriteBatch mSprieBatch; // thang nay chinh la thang ve, giong canvas trong view
        BirdEntity birdEntity;
        int mX;
        // xay dung ham khoi tao cho thang lop camera nay thoi
        public ScreenAdapter() {
            mCamera = new OrthographicCamera();
            // set kich thuoc cho thang camera, thoi cu fix cung truoc
            mCamera.setToOrtho(true, WIDTH, HEIGHT);

            mSprieBatch = new SpriteBatch();
            mSprieBatch.setProjectionMatrix(mCamera.combined); // set ma tran hien thi theo thang camera
            Gdx.input.setInputProcessor(this); // minh goi cai nay ra de bat cac su kien khi xu ly
             birdEntity = new BirdEntity(mListBirdRegion, BIRD_WIDTH, BIRD_HEIGHT, 40, 500);
            // ok roi, gio minh lap trinh theo huong doi tuong, thi gio tuong tuong ra da

            // doi tuong co con chim, tao lop con chim.

        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            mX = screenX;
            return super.touchDown(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {

            int mCurrentX = screenX;
            if (mCurrentX > mX) {
                if (mPos < 0)
                    mPos += 5;
            } else {
                if (mCurrentX < mX) {
                    if (mPos > -WIDTH)
                        mPos -= 5;
                }
            }
            mX = mCurrentX;
            return super.touchDragged(screenX, screenY, pointer);
        }
        // thu render xem chay ko nhe
        @Override
        public void render(float v) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            mSprieBatch.begin();

            // ve thoi

            birdEntity.runFly(mSprieBatch, mPos);
            mSprieBatch.end();
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

        }
    }

    @Override
    public void dispose() {
        super.dispose();
        mAssetManager.dispose();
    }
}

package com.ikame.dayscreenlivewallpaper.ui.dubi.models;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Administrator on 17/10/2017.
 * moi doi tuong chim se co 1 list cac region
 * region la cac hinh anh no bay do
 */

public class BirdEntity {
    // thoi tam thoi no co chieu ngang vao chieu cao la thuoc tinh, them 2 thuoc tinh

    public static final int RUN = 0;
    public static final int SLOW = 1;
    public static final int TURN = 2;
    public static final int FAST = 3;

    private int mWidth;
    private int mHeight;

    private int mX;
    private int mY;
    private float stateTime;

    private int state = 0;

    private Array<TextureAtlas.AtlasRegion> mListRegion;
    private Random random = new Random();
    // gio den phuong thuc cua no
    private Vector2 mPosition; // vi tri bay
    private Vector2 mVeLocity; // van toc
    private Vector2 mAcceleration; // gia toc
    private Animation animation;

    public BirdEntity(Array<TextureAtlas.AtlasRegion> listRegion, int mWidth, int mHeight, int mX, int mY) {
        mListRegion = listRegion;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        // tao animation de doi cac hinh anh region
        animation = new Animation(0.04f, mListRegion);
        // set them tinh nang lap lai tu dau cho no neu da chay het region
        animation.setPlayMode(Animation.LOOP);
        mPosition = new Vector2(mX, mY);
        mVeLocity = new Vector2(120, 30); // van toc anh fix cung 1 gia tri truoc
        mAcceleration = new Vector2(100, 300);

        // this.mX = mX;
        //  this.mY = mY

    }

    // con chim bat dau bay o day
    public void runFly(SpriteBatch spriteBatch, int pos) {

        stateTime += Gdx.graphics.getDeltaTime();
        update();
        //param1: truyen list region anim vao, tuc la cac khung hinh /s
        //param 2,3: vi tri
        //param 4,5: kich thuoc
        spriteBatch.draw(animation.getKeyFrame(stateTime), mPosition.x + pos, mPosition.y,  mWidth / 2, mHeight / 2,  mWidth, mHeight, 1, 1, 0);
    }

    private void update() {
        switch (state) {
            case RUN:
                runBird();
                break;
            case TURN:
                turnBird();
                break;
            case FAST:
                fastBird();
                break;
            case SLOW:
                slowBird();
                break;
        }
        fly();
    }

    private void slowBird() {

    }

    private void fastBird() {

    }

    private void turnBird() {

    }

    private void runBird() {
        mVeLocity.add(mAcceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (mVeLocity.x > 200) {
            mVeLocity.x = 120;
            int valueRandom = random.nextInt(2);
            if (valueRandom == 0) {
                mVeLocity.y = 30;
            } else {
                mVeLocity.y = -30;
            }

        }
    }

    private void fly() {
        mPosition.add(mVeLocity.cpy().scl(Gdx.graphics.getDeltaTime()));
    }

}

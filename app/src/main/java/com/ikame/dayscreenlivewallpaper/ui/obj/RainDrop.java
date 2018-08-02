package com.ikame.dayscreenlivewallpaper.ui.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.Random;

/**
 * Created by MyPC on 21/05/2016.
 */
public class RainDrop {

    private TextureRegion region;
    private Vector2 pos, size, vel, ace;
    private float rat;
    private boolean draw;
    private int x, y;

    public RainDrop(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    public void init() {
        region = Assets.assetRain.rain1;
        pos = new Vector2(x, y);
        rat = getRandom(3, 5) / (float) 10;
        size = new Vector2(region.getRegionWidth() * rat, region.getRegionHeight() * rat);
        vel = new Vector2(0, 400);
        ace = new Vector2(0, 400);
        draw = false;
    }


    public void draw(SpriteBatch batch, float runTime, int mPos) {
        vel.add(ace.cpy().scl(Gdx.graphics.getDeltaTime()));
        pos.add(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
        batch.draw(region, pos.x + mPos, pos.y, size.x, size.y);
        if (pos.y > Constant.GRASS_Y + Constant.HEIGHT / 2) {
            init();
        }
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public int getRandom(int a, int b) {
        return new Random().nextInt(b - a) + a;
    }

}

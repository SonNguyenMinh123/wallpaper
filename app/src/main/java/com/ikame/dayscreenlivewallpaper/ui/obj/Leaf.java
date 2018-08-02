package com.ikame.dayscreenlivewallpaper.ui.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 18/05/2016.
 */
public class Leaf {

    private TextureRegion region;
    private List<TextureRegion> list;
    private Vector2 pos, size, vel, ace;
    private float rot;
    private float rat;
    private int h;
    private int speed;
    private boolean draw;
    private int x, y;
    private int rY;

    public Leaf(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    public void init() {
        list = Assets.assetLeaf.list;
        region = list.get(new Random().nextInt(list.size()));
        pos = new Vector2(x, y);
        rat = (new Random().nextInt(4) + 3) / (float) 10;
        size = new Vector2(region.getRegionWidth() * rat, region.getRegionHeight() * rat);
        rot = new Random().nextInt(360);
        vel = new Vector2(new Random().nextInt(30) + 30, new Random().nextInt(50) + 100);
        int tmp = new Random().nextInt(2);
        if (tmp == 0) {
            vel.x *= -1;
        }
        int r = new Random().nextInt(2);
        if (r == 0) {
            h = 1;
        } else {
            h = -1;
        }
        draw = false;
        speed = new Random().nextInt(50) + 80;
        rY = new Random().nextInt(Constant.GRASS_Y / 2);
    }


    public void draw(SpriteBatch batch, float runTime, int mPos) {
        update(runTime);
        batch.draw(region, pos.x + mPos, pos.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rot);
        if (pos.y > Constant.GRASS_Y + Constant.GRASS_HEIGHT / 2) {
            init();
        }
    }

    public void update(float runTime) {
        if (pos.y > rY) {
            rY = Constant.HEIGHT;
            vel.x *= -1;
        }
        pos.add(vel.cpy().scl(Gdx.graphics.getDeltaTime()));
        rot = h * ((runTime * speed) % 360);
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

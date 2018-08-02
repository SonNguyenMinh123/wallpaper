package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MyPC on 19/05/2016.
 */
public class Bough {

    private Context context;
    private SharedPreferences prefs;
    private int condition;
    private TextureRegion region;
    private Vector2 pos, size;
    private int h, speed;
    private float rot;
    private boolean drop;
    private List<Leaf> leafs;
    private List<RainDrop> rains;

    public Bough(Context context, TextureRegion region, Vector2 pos, Vector2 size) {
        this.context = context;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.region = region;
        this.pos = pos;
        this.size = size;
        h = 1;
        speed = 3;
        rot = new Random().nextInt(11) - 5;
        drop = false;
        leafs = new ArrayList<>();
        init(new Random().nextInt(4) + 4);
        rains = new ArrayList<>();
        initRain(new Random().nextInt(4) + 10);
    }

    public void init(int number) {
        leafs = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int x = getRandom((int) pos.x, (int) (pos.x + size.x));
            int y = getRandom((int) pos.y, (int) (pos.y + size.y / 3));
            Leaf leaf = new Leaf(x, y);
            leafs.add(leaf);
        }
    }

    public void initRain(int number) {
        rains = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int x = getRandom((int) pos.x, (int) (pos.x + size.x));
            int y = getRandom((int) pos.y, (int) (pos.y + size.y / 3));
            RainDrop rainDrop = new RainDrop(x, y);
            rains.add(rainDrop);
        }
    }

    public void draw(SpriteBatch batch, int mPos, float x, float y, float runTime) {
        update();
        batch.draw(region, pos.x + mPos, pos.y, x, y, size.x, size.y, 1, 1, rot);
        condition = prefs.getInt(Constant.CONDITIONS, Constant.CLOUDS);
        if (condition == Constant.RAIN) {
            drawRain(batch, mPos, runTime);
        } else {
            drawLeaf(batch, mPos, runTime);
        }
    }

    public void drawLeaf(SpriteBatch batch, int mPos, float runTime) {
        for (Leaf leaf : leafs) {
            if (leaf.isDraw()) {
                leaf.draw(batch, runTime, mPos);
            }
        }
    }

    public void drawRain(SpriteBatch batch, int mPos, float runTime) {
        for (RainDrop rainDrop : rains) {
            if (rainDrop.isDraw()) {
                rainDrop.draw(batch, runTime, mPos);
            }
        }
    }

    public void update() {
        if (Math.abs(rot) > 5) {
            rot = 5 * h;
            speed = 3;
            h *= -1;
        }
        rot += h * Gdx.graphics.getDeltaTime() * (new Random().nextInt(2) + speed);
    }

    public void onTouch(int screenX, int screenY) {
        if (touch(screenX, screenY)) {
            speed = 10;
            touchLeaf();
            touchRain();
        }
    }

    public void touchRain() {
        int num = new Random().nextInt(2) + 4;
        while (num != 0) {
            for (int i = 0; i < rains.size(); i++) {
                RainDrop rainDrop = rains.get(i);
                if (!rainDrop.isDraw()) {
                    rainDrop.setDraw(true);
                    break;
                }
                if (i == rains.size() - 1) num = 1;
            }
            num--;
        }
    }

    public void touchLeaf() {
        int num = new Random().nextInt(2) + 2;
        while (num != 0) {
            for (int i = 0; i < leafs.size(); i++) {
                Leaf leaf = leafs.get(i);
                if (!leaf.isDraw()) {
                    leaf.setDraw(true);
                    break;
                }
                if (i == leafs.size() - 1) num = 1;
            }
            num--;
        }
    }

    public boolean touch(int screenX, int screenY) {
        if (screenX >= pos.x - Constant.TREE_RADIUS && screenX <= pos.x + size.x + Constant.TREE_RADIUS
                && screenY >= pos.y - Constant.TREE_RADIUS && screenY <= pos.y + size.y + Constant.TREE_RADIUS) {
            return true;
        }
        return false;
    }

    public int getRandom(int a, int b) {
        return new Random().nextInt(b - a) + a;
    }

}

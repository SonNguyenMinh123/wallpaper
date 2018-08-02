package com.ikame.dayscreenlivewallpaper.ui.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 12/05/2016.
 */
public class Mountain {

    private TextureRegion mountain;
    private Sprite sprite1;
    private Vector2 position;
    private int width, height;

    public Mountain() {
        mountain = Assets.assetMountain.mountain1;
        sprite1 = new Sprite(mountain);
        init();
    }

    private void init() {
        int x, y;
        this.width = Constant.WIDTH * 2;
        this.height = Constant.MOUNTAIN_HEIGHT;
        x = 0;
        y = Constant.MOUNTAIN_Y;
        this.position = new Vector2(x, y);

    }

    public void draw(SpriteBatch batch, int mPos) {
//        batch.setColor(sprite1.getColor().r, sprite1.getColor().g, sprite1.getColor().b, 1f);
        batch.draw(mountain, position.x + mPos / 2, position.y, width, height);
    }
}
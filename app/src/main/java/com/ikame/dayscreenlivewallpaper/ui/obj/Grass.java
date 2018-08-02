package com.ikame.dayscreenlivewallpaper.ui.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 12/05/2016.
 */
public class Grass {
    private TextureRegion grass;
    private Sprite sprite;
    private Vector2 position;
    private int width, height;

    public Grass() {
        Texture texture = new Texture(Gdx.files.internal("grass/grass2.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        grass = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        grass.flip(false, true);
        sprite = new Sprite(grass);
        init();
    }

    public void init() {
        int x, y;
        this.width = Constant.WIDTH * 2;
        this.height = Constant.GRASS_HEIGHT;
        y = Constant.GRASS_Y;
        x = 0;
        this.position = new Vector2(x, y);
    }


    public void draw(SpriteBatch batch, int mPos) {
//        batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 1f);
        batch.draw(grass, position.x + mPos, position.y, width, height);
    }
}

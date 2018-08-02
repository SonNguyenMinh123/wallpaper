package com.ikame.dayscreenlivewallpaper.ui.dubi;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Administrator on 17/10/2017.
 */

public class BasicAtlas implements ApplicationListener {
    private SpriteBatch batch;
    private Pixmap pixmap;
    private Texture texture;
    private Sprite sprite;


    @Override
    public void create() {
        batch = new SpriteBatch();
        // Width: 256, height: 128
        pixmap = new Pixmap(256, 128, Pixmap.Format.RGBA8888);
        // Fill red color
        pixmap.setColor(Color.RED);
        pixmap.fill();
        // Draw 2 lines
        pixmap.setColor(Color.BLACK);
        pixmap.drawLine(0, 0, pixmap.getWidth() - 1, pixmap.getHeight() - 1);
        pixmap.drawLine(0, pixmap.getHeight() - 1, pixmap.getWidth() - 1, 0);
        // Draw a circle in the middle
        pixmap.setColor(Color.YELLOW);
        pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
        texture = new Texture(pixmap);
        pixmap.dispose();
        sprite = new Sprite(texture);


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();

    }
}

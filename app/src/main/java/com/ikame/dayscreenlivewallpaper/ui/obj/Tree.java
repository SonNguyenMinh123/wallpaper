package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 18/05/2016.
 */
public class Tree {

    private Context context;
    private Bough b37, b36, b39, b32, b31, b26, b34;

    private TextureAtlas.AtlasRegion L26;
    private TextureAtlas.AtlasRegion L31;
    private TextureAtlas.AtlasRegion L32;
    private TextureAtlas.AtlasRegion L34;
    private TextureAtlas.AtlasRegion L36;
    private TextureAtlas.AtlasRegion L37;
    private TextureAtlas.AtlasRegion L39;
    private Vector2 L37_pos, L37_size;
    private Vector2 L36_pos, L36_size;
    private Vector2 L39_pos, L39_size;
    private Vector2 L32_pos, L32_size;
    private Vector2 L31_pos, L31_size;
    private Vector2 L26_pos, L26_size;
    private Vector2 L34_pos, L34_size;

    public Tree(Context context) {
        this.context = context;

        L26 = Assets.assetTree.L26;
        L31 = Assets.assetTree.L31;
        L32 = Assets.assetTree.L32;
        L34 = Assets.assetTree.L34;
        L36 = Assets.assetTree.L36;
        L37 = Assets.assetTree.L37;
        L39 = Assets.assetTree.L39;

        L37_size = new Vector2(Constant.TREE_WIDTH, (L37.getRegionHeight() * Constant.TREE_WIDTH) / L37.getRegionWidth());
        L37_pos = new Vector2(Constant.WIDTH / 2, Constant.HEIGHT - Constant.GRASS_HEIGHT / 2 - L37_size.y * 2);

        L36_size = new Vector2(Constant.TREE_WIDTH, (L36.getRegionHeight() * Constant.TREE_WIDTH) / L36.getRegionWidth());
        L36_pos = new Vector2(L37_pos.x - L37_size.y / 2, L37_pos.y - L36_size.y);

        L39_size = new Vector2(Constant.TREE_WIDTH, (L39.getRegionHeight() * Constant.TREE_WIDTH) / L39.getRegionWidth());
        L39_pos = new Vector2(L36_pos.x - L39_size.x / 3.6f, L36_pos.y + L39_size.y / 2.2f);

        L32_size = new Vector2(Constant.TREE_WIDTH, (L32.getRegionHeight() * Constant.TREE_WIDTH) / L32.getRegionWidth());
        L32_pos = new Vector2(L36_pos.x + L39_size.y * 1.2f, L36_pos.y + L32_size.y / 4);

        L31_size = new Vector2(Constant.TREE_WIDTH, (L31.getRegionHeight() * Constant.TREE_WIDTH) / L31.getRegionWidth());
        L31_pos = new Vector2(L37_pos.x + L31_size.x / 3, L37_pos.y - L31_size.y * 1.2f);

        L26_size = new Vector2(Constant.TREE_WIDTH, (L31.getRegionHeight() * Constant.TREE_WIDTH) / L31.getRegionWidth());
        L26_pos = new Vector2(L37_pos.x + L26_size.x / 4, L37_pos.y - L26_size.y / 1.5f);

        L34_size = new Vector2(Constant.TREE_WIDTH, (L34.getRegionHeight() * Constant.TREE_WIDTH) / L34.getRegionWidth());
        L34_pos = new Vector2(L37_pos.x - L34_size.x / 2, L37_pos.y - L34_size.y / 2);

        b37 = new Bough(context, L37, L37_pos, L37_size);
        b36 = new Bough(context, L36, L36_pos, L36_size);
        b39 = new Bough(context, L39, L39_pos, L39_size);
        b32 = new Bough(context, L32, L32_pos, L32_size);
        b31 = new Bough(context, L31, L31_pos, L31_size);
        b26 = new Bough(context, L26, L26_pos, L26_size);
        b34 = new Bough(context, L34, L34_pos, L34_size);
    }

    public void draw(SpriteBatch batch, float runTime, int mPos) {
        batch.draw(L37, L37_pos.x + mPos, L37_pos.y, L37_size.x / 2, L37_size.y / 2, L37_size.x, L37_size.y, 1, 1, 90);
        b36.draw(batch, mPos, L36_size.x, L36_size.y, runTime);
        b39.draw(batch, mPos, L39_size.x, L39_size.y, runTime);
        b32.draw(batch, mPos, 0, L32_size.y, runTime);
        b31.draw(batch, mPos, L31_size.x / 3, L31_size.y, runTime);
        b26.draw(batch, mPos, 0, L26_size.y, runTime);
        b34.draw(batch, mPos, L34_size.x, L34_size.y, runTime);
    }

    public void onTouch(int screenX, int screenY) {
        b36.onTouch(screenX, screenY);
        b39.onTouch(screenX, screenY);
        b32.onTouch(screenX, screenY);
        b31.onTouch(screenX, screenY);
        b26.onTouch(screenX, screenY);
        b34.onTouch(screenX, screenY);

    }
}

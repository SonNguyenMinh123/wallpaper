package com.ikame.dayscreenlivewallpaper.ui.obj;

import android.content.Context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.ikame.dayscreenlivewallpaper.common.Assets;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by Administrator on 19/10/2017.
 */

public class SecondTree {

    private Context context;
    private Bough leav3Bough, l_branchBough, banch3, banch4, l1, l2, l3, l4, l5, l6, l7, l8, r1, r2, r3, r4, r5, r6, r7, r8, rootBough;
    //
    private TextureAtlas.AtlasRegion b1;
    private TextureAtlas.AtlasRegion b2;
    private TextureAtlas.AtlasRegion b3;
    private TextureAtlas.AtlasRegion b4;
    private TextureAtlas.AtlasRegion l_branch;
    private TextureAtlas.AtlasRegion L2;
    private TextureAtlas.AtlasRegion Leaves3;
    private TextureAtlas.AtlasRegion L4;
    private TextureAtlas.AtlasRegion L5;
    private TextureAtlas.AtlasRegion L6;
    private TextureAtlas.AtlasRegion L7;
    private TextureAtlas.AtlasRegion L8;
    private TextureAtlas.AtlasRegion R1;
    private TextureAtlas.AtlasRegion R2;
    private TextureAtlas.AtlasRegion R3;
    private TextureAtlas.AtlasRegion R4;
    private TextureAtlas.AtlasRegion R5;
    private TextureAtlas.AtlasRegion R6;
    private TextureAtlas.AtlasRegion R7;
    private TextureAtlas.AtlasRegion R8;
    private TextureAtlas.AtlasRegion roott;

    private Vector2 Banch1_pos, banch1_size;
    private Vector2 Banch2_pos, banch2_size;
    private Vector2 Banch3_pos, banch3_size;
    private Vector2 Banch4_pos, banch4_size;
    private Vector2 L1_pos, L1_size;
    private Vector2 L2_pos, L2_size;
    private Vector2 L3_pos, L3_size;
    private Vector2 L4_pos, L4_size;
    private Vector2 L5_pos, L5_size;
    private Vector2 L6_pos, L6_size;
    private Vector2 L7_pos, L7_size;
    private Vector2 L8_pos, L8_size;
    private Vector2 r1_pos, R1_size;
    private Vector2 r2_pos, R2_size;
    private Vector2 r3_pos, R3_size;
    private Vector2 r4_pos, R4_size;
    private Vector2 r5_pos, R5_size;
    private Vector2 r6_pos, R6_size;
    private Vector2 l_branch_pos, l_branch_size;
    private Vector2 leav3_pos, leav3_size;
    private Vector2 root_pos, root_size;

    public SecondTree(Context context) {
        this.context = context;

        l_branch = Assets.assetTree2.l_branch;
        Leaves3 = Assets.assetTree2.leaves3Region;
        roott = Assets.assetTree2.L39_2;
        float tyle = Constant.TREE_WIDTH2 / roott.getRegionWidth();

        // init position, size
        root_size = new Vector2(Constant.TREE_WIDTH2, (Constant.TREE_WIDTH2 / roott.getRegionWidth()) * roott.getRegionHeight());
        root_pos = new Vector2(Constant.WIDTH / 2 + 700, Constant.HEIGHT - Constant.GRASS_HEIGHT / 2 - root_size.y * 1.5f);

        leav3_size = new Vector2(Leaves3.getRegionWidth() * tyle, tyle * Leaves3.getRegionHeight());
        leav3_pos = new Vector2(root_pos.x - root_size.x / 1.8f, root_pos.y - leav3_size.y + 50);

        l_branch_size = new Vector2(l_branch.getRegionWidth() * tyle, tyle * l_branch.getRegionHeight());
        l_branch_pos = new Vector2(root_pos.x - root_size.x / 1.8f + 60, root_pos.y - leav3_size.y + 60);

        // init Bough
        rootBough = new Bough(context, roott, root_pos, root_size);
        leav3Bough = new Bough(context, Leaves3, leav3_pos, leav3_size);
        l_branchBough = new Bough(context, l_branch, l_branch_pos, l_branch_size);
        banch4 = new Bough(context, b4, Banch4_pos, banch4_size);
        /*
        l1 = new Bough(context, l_branch, L1_pos, L1_size);
        l2 = new Bough(context, L2, L2_pos, L2_size);
        l3 = new Bough(context, L3, L3_pos, L3_size);
        l4 = new Bough(context, L4, L4_pos, L4_size);
        l5 = new Bough(context, L5, L5_pos, L5_size);
        l6 = new Bough(context, L6, L6_pos, L6_size);
        l7 = new Bough(context, L7, L7_pos, L7_size);
        l8 = new Bough(context, L8, L8_pos, L8_size);

        r1 = new Bough(context, R1, r1_pos, R1_size);
        r2 = new Bough(context, R2, r2_pos, R2_size);
        r3 = new Bough(context, R3, r3_pos, R3_size);
        r4 = new Bough(context, R4, r4_pos, R4_size);
        r5 = new Bough(context, R5, r5_pos, R5_size);
        r6 = new Bough(context, R6, r6_pos, R6_size);
        r7 = new Bough(context, R7, r7_pos, R7_size);
        r8 = new Bough(context, R8, r8_pos, R8_size);
        */

    }

    public void draw(SpriteBatch batch, float runTime, int mPos) {
        batch.draw(roott, root_pos.x + mPos, root_pos.y, root_size.x / 2, root_size.y / 2, root_size.x, root_size.y, 1, 1, 0);
        leav3Bough.draw(batch, mPos, leav3_size.x, leav3_size.y, runTime);
        l_branchBough.draw(batch, mPos, l_branch_size.x, l_branch_size.y, runTime);

        /*
        banch2.draw(batch, mPos, banch2_size.x, banch2_size.y, runTime);
        banch3.draw(batch, mPos, 0, banch3_size.y, runTime);
        banch4.draw(batch, mPos, banch4_size.x / 3, banch4_size.y, runTime);
        l1.draw(batch, mPos, 0, L1_size.y, runTime);
        l2.draw(batch, mPos, L1_size.x, L1_size.y, runTime);
        */

    }

    public void onTouch(int screenX, int screenY) {
        /*
        banch1.onTouch(screenX, screenY);
        banch2.onTouch(screenX, screenY);
        banch3.onTouch(screenX, screenY);
        banch4.onTouch(screenX, screenY);
        l1.onTouch(screenX, screenY);
        l2.onTouch(screenX, screenY);
        */

    }
}

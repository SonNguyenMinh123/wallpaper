package com.ikame.dayscreenlivewallpaper.ui.activities;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import com.ikame.dayscreenlivewallpaper.R;
import com.ikame.dayscreenlivewallpaper.ui.game.MyGame;
import com.ikame.dayscreenlivewallpaper.ui.service.MyWallpaperService;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private MyGame myGame;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
        this.finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    private void initUI() {
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT
                , new ComponentName(MainActivity.this, MyWallpaperService.class));
        startActivity(intent);
    }
}

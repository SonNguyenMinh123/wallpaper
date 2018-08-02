package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.activity;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.R;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.service.MyServiceWallpaper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        this.finish();
    }

    private void initUI() {
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT
                , new ComponentName(MainActivity.this, MyServiceWallpaper.class));
        startActivity(intent);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}

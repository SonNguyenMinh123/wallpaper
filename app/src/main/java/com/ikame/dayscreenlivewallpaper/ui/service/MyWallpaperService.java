package com.ikame.dayscreenlivewallpaper.ui.service;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.ikame.dayscreenlivewallpaper.ui.game.MyGame;

/**
 * Created by Administrator on 17/10/2017.
 * muc dich cua lop service nay duoc sinh ra la de ket noi voi service wallpaper cua he thong
 * no co the chay ngam
 */

public class MyWallpaperService extends AndroidLiveWallpaperService {
    private ApplicationListener listener;

    /**
     * khi service nay duoc start thi no se nhay vao ham nay
     */
    @Override
    public void onCreateApplication() {
        super.onCreateApplication();

        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();

        // minh ko su dung openGL,compass, hay wakelock,... nen dong het may cai nay lai
        configuration.useGL20 = false;
        configuration.useCompass = false;
        configuration.useWakelock = false;
        configuration.useAccelerometer = false;

        // cho phep su dung touch de minh click vao chim thi bat su kien
        configuration.getTouchEventsForLiveWallpaper = true;

        // minh config xong roi, gio den luot chay thang camera
        // tuc la hien thi no ra man hinh
        // gio di xay dung
        listener = new MyGame(this);
        initialize(listener, configuration); // thang nay se goi den lop xay dung

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener.dispose();
    }
}

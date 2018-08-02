package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Constant;

/**
 * Created by Administrator on 26/10/2017.
 */

public class MyReceiver extends WakefulBroadcastReceiver {
    private Context context;
    private SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(Constant.TIME_OF_DAY, getTimeDay()).apply();
        prefs.edit().putInt(Constant.TIME_OF_DAY, Constant.SUNSET).apply();

    }

    // Get time of Day
    public int getTimeDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 00 && hour < 5) {
            Log.e("0<Son<5", "AAA");
            return Constant.NIGHT;
        }
        if (hour >= 5 && hour < 7) {
            Log.e("5<Son<7", "AAA");
            return Constant.SUNRISE;
        }
        if (hour >= 7 && hour < 17) {
            Log.e("7<Son<17", "AAA");
            return Constant.DAY;
        }
        if (hour >= 17 && hour < 19) {
            Log.e("17<Son<19", "AAA");
            return Constant.SUNSET;
        }
        if (hour >= 19 && hour < 24) {
            Log.e("19<Son<24", "AAA");
            return Constant.NIGHT;
        }
        return Constant.NIGHT;
    }
}

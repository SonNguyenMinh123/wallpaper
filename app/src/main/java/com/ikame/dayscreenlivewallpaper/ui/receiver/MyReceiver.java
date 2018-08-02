package com.ikame.dayscreenlivewallpaper.ui.receiver;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.ikame.dayscreenlivewallpaper.R;
import com.ikame.dayscreenlivewallpaper.common.Constant;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;

import java.util.Calendar;

/**
 * Created by MyPC on 20/04/2016.
 */
public class MyReceiver extends WakefulBroadcastReceiver implements LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    boolean isNetworkEnabled;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private SharedPreferences prefs;
    private Context context;
    private WeatherClient client;
    private LocationManager locationManager;
    private Location location = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(Constant.TIME_OF_DAY, timeOfDay()).apply();

        if (isNetworkAvailable()) {

            buildClientWeather();
            getLocationValue();
        }
        int tmp = prefs.getInt(Constant.RANDOM, 1);
        tmp *= -1;
        prefs.edit().putInt(Constant.RANDOM, tmp).apply();

    }

    public void getLocationValue() {
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        getWeatherByPos(longitude, latitude);
                    }
                }

            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildClientWeather() {
        WeatherClient.ClientBuilder builder = new WeatherClient.ClientBuilder();
        WeatherConfig config = new WeatherConfig();
        config.ApiKey = context.getResources().getString(R.string.forecastio_key);
        try {
            client = (builder)
                    .attach(context)
                    .provider(new OpenweathermapProviderType())
                    .httpClient(com.survivingwithandroid.weather.lib.client.volley.WeatherClientDefault.class)
                    .config(config)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int timeOfDay() {
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

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void getWeatherByPos(double lon, double lat) {
        client.getCurrentCondition(new WeatherRequest(lon, lat), new WeatherClient.WeatherEventListener() {
            @Override
            public void onWeatherRetrieved(CurrentWeather weather) {
                if (weather != null) {

                    Log.d("condition", prefs.getInt(Constant.CONDITIONS, 0) + "");

                    String city = weather.weather.location.getCity();
                    String cou = weather.weather.location.getCountry();
                    String region = weather.weather.location.getRegion();
                    String c = city + " - " + cou;
                    float tem = weather.weather.temperature.getTemp();
                    String mCondition = weather.weather.currentCondition.getCondition();
                    int condition = 0;
                    if (mCondition.equals("Rain")) {
                        condition = Constant.RAIN;
                    }
                    if (mCondition.equals("Snow")) {
                        condition = Constant.SNOW;
                    }
                    if (mCondition.equals("Clear")) {
                        condition = Constant.CLEAR;
                    }
                    if (mCondition.equals("Clouds")) {
                        condition = Constant.CLOUDS;
                    }
                    if (condition == 0) {
                        condition = Constant.CLOUDS;
                    }
                    prefs.edit().putFloat(Constant.WEATHER, tem).apply();
                    prefs.edit().putString(Constant.CITY, c).apply();
                    prefs.edit().putInt(Constant.CONDITIONS, condition).apply();
                }
            }

            @Override
            public void onWeatherError(WeatherLibException wle) {

            }

            @Override
            public void onConnectionError(Throwable t) {

            }
        });
    }

    public void setAlarm(Context context) {
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3, alarmIntent);

        ComponentName receiver = new ComponentName(context, MyReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context) {
        if (alarmMgr != null) {
            alarmMgr.cancel(alarmIntent);
        }

        ComponentName receiver = new ComponentName(context, MyReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

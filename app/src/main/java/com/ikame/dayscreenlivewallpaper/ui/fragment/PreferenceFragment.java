package com.ikame.dayscreenlivewallpaper.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;

import com.ikame.dayscreenlivewallpaper.R;
import com.ikame.dayscreenlivewallpaper.common.Constant;

/**
 * Created by MyPC on 24/05/2016.
 */
public class PreferenceFragment extends android.preference.PreferenceFragment {
    private SharedPreferences preferences;

    public PreferenceFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_setting);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        preview();

        final CheckBoxPreference setVolume = (CheckBoxPreference) findPreference("volume");
        boolean p = preferences.getBoolean(Constant.VOLUME, true);
        setVolume.setChecked(p);
        setVolume.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean p = preferences.getBoolean(Constant.VOLUME, true);
                if (p) {
                    p = false;
                } else {
                    p = true;
                }
                preferences.edit().putBoolean(Constant.VOLUME, p).apply();
                return true;
            }
        });

        final CheckBoxPreference show = (CheckBoxPreference) findPreference("show");
        boolean s = preferences.getBoolean(Constant.SHOW, true);
        show.setChecked(s);
        show.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean s = preferences.getBoolean(Constant.SHOW, true);
                if (s) {
                    s = false;
                } else {
                    s = true;
                }
                preferences.edit().putBoolean(Constant.SHOW, s).apply();
                return true;
            }
        });

        final Preference tem = findPreference("tem");
        boolean te = preferences.getBoolean(Constant.TEMP, true);
        if (te) {
            tem.setSummary(Constant.C);
        } else {
            tem.setSummary(Constant.F);
        }

        tem.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                boolean te = preferences.getBoolean(Constant.TEMP, true);
                if (te) {
                    te = false;
                } else {
                    te = true;
                }
                preferences.edit().putBoolean(Constant.TEMP, te).apply();
                if (te) {
                    tem.setSummary(Constant.C);
                } else {
                    tem.setSummary(Constant.F);
                }
                return true;
            }
        });

        final Preference rate = findPreference("Rate");
        rate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPreferenceClick(Preference preference) {
                try {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getContext().getPackageName())));

                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
                return true;
            }
        });

    }

    public void preview() {
        final Preference p = findPreference("setWallpaper");
        p.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                getActivity().finish();
                return true;
            }
        });
    }
}

package ro.customsettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import ro.customsettings.preference.MultiSelectListPrefrence;
import ro.customsettings.preference.TimePickerPreference;

/**
 * Created by Calin Martinconi on 2/7/14.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs_settings);
        setContentView(R.layout.activity_settings);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        init();

    }

    public void init(){

        TimePickerPreference timePickerPreference = (TimePickerPreference) findPreference("time_picker");
        timePickerPreference.setSummary("12:00");
        timePickerPreference.setTimeVales("12:00");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("time_picker", "12:00");
        editor.commit();

        MultiSelectListPrefrence multiSelectListPrefrence = (MultiSelectListPrefrence) findPreference("repeat_days");
        multiSelectListPrefrence.setSummary("Monday, Friday");
        multiSelectListPrefrence.setDaysValues("Monday, Friday");
        editor.putString("repeat_days", "Monday, Friday");
        editor.commit();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String s) {

        if (s.equals("time_picker")) {
            TimePickerPreference preference = (TimePickerPreference) findPreference(s);
            preference.setSummary(sharedPrefs.getString(s, ""));

        } else if (s.equals("repeat_days")) {
            MultiSelectListPrefrence preference = (MultiSelectListPrefrence) findPreference(s);
            preference.setSummary(sharedPrefs.getString(s,""));
        }

      }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}

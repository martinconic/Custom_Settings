package ro.customsettings.preference;

import android.app.Activity;
import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ro.customsettings.R;

/**
 * Created by calin on 28.03.2014.
 */
public class MultiSelectListPrefrence extends DialogPreference {

    private boolean luni;
    private boolean marti;
    private boolean miercuri;
    private boolean joi;
    private boolean vineri;
    private boolean sambata;
    private boolean duminica;

    View view;

    public MultiSelectListPrefrence(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }
    @Override
    protected View onCreateDialogView() {
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        view = inflater.inflate(R.layout.preference_weekdays, null);
        return view;
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        ((CheckBox) v.findViewById(R.id.checkbox_luni)).setChecked(luni);
        ((CheckBox) v.findViewById(R.id.checkbox_marti)).setChecked(marti);
        ((CheckBox) v.findViewById(R.id.checkbox_miercuri)).setChecked(miercuri);
        ((CheckBox) v.findViewById(R.id.checkbox_joi)).setChecked(joi);
        ((CheckBox) v.findViewById(R.id.checkbox_vineri)).setChecked(vineri);
        ((CheckBox) v.findViewById(R.id.checkbox_sambata)).setChecked(sambata);
        ((CheckBox) v.findViewById(R.id.checkbox_duminica)).setChecked(duminica);

    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if(positiveResult){
            luni =((CheckBox) view.findViewById(R.id.checkbox_luni)).isChecked();
            marti = ((CheckBox) view.findViewById(R.id.checkbox_marti)).isChecked();
            miercuri = ((CheckBox) view.findViewById(R.id.checkbox_miercuri)).isChecked();
            joi = ((CheckBox) view.findViewById(R.id.checkbox_joi)).isChecked();
            vineri = ((CheckBox) view.findViewById(R.id.checkbox_vineri)).isChecked();
            sambata = ((CheckBox) view.findViewById(R.id.checkbox_sambata)).isChecked();
            duminica = ((CheckBox) view.findViewById(R.id.checkbox_duminica)).isChecked();

            String days = ((luni? getContext().getString(R.string.monday) + ", " : "")   +
                    (marti? getContext().getString(R.string.tuesday) + ", ": "")  +
                    (miercuri? getContext().getString(R.string.wednesday) + ", ": "")  +
                    (joi? getContext().getString(R.string.thursday) + ", ": "")  +
                    (vineri? getContext().getString(R.string.friday) + ", ": "")  +
                    (sambata? getContext().getString(R.string.saturday) + ", ": "")  +
                    (duminica? getContext().getString(R.string.sunday) : "")) ;

            days = days.substring(days.length()-2,days.length()).equals(", ")? days.substring(0,days.length()-2) : days;

            if (callChangeListener(days)) {
                persistString(days);
            }
        }
    }


    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        luni = false;
        marti = false;
        miercuri = false;
        joi = false;
        vineri = false;
        sambata = false;
        duminica = false;
    }

    public void setDaysValues(String weekDays){

        List<String> repeatDays = Arrays.asList(weekDays.split(", "));

        Iterator<String> iterator = repeatDays.iterator();
        while(iterator.hasNext()){
            String day = iterator.next();
            if (day.equals(getContext().getString(R.string.monday))) {
                luni = true;
            } else if (day.equals(getContext().getString(R.string.tuesday))) {
                marti = true;
            } else if (day.equals(getContext().getString(R.string.wednesday))) {
                miercuri = true;
            } else if (day.equals(getContext().getString(R.string.thursday))) {
                joi = true;
            } else if (day.equals(getContext().getString(R.string.friday))) {
                vineri = true;
            } else if (day.equals(getContext().getString(R.string.saturday))) {
                sambata = true;
            } else if (day.equals(getContext().getString(R.string.sunday))) {
                duminica = true;
            }

        }
    }
}

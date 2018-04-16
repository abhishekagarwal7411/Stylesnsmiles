package com.example.abhishek.stylesnsmiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BookingAppointment extends AppCompatActivity {
    int i;
    int buttons = 5;;
    RadioGroup rgp;
    String[] str={"Facials","Waxing","Nail care","Eye care","Foot care","Make up"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        AppCompatRadioButton[] rb = new AppCompatRadioButton[buttons];

       rgp = (RadioGroup) findViewById(R.id.radio_group);
        rgp.setOrientation(LinearLayout.VERTICAL);

        for (i = 1; i <= buttons; i++) {
            RadioButton rbn = new RadioButton(this);
            rbn.setId(i + 1000);
            rbn.setText(str[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            rbn.setChecked(true);
            rbn.setPadding(10,10,10,10);
            rgp.addView(rbn);
        }

    }

}

package com.example.a2study_projektkurs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LernenKartei_Activity extends AppCompatActivity {

    ImageButton backbutton5;
    Dialog zeigInfo6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernen_kartei_);

        zeigInfo6 = new Dialog(this);

        backbutton5 = findViewById(R.id.backbutton5);

        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LernenKartei_Activity.this.finish();
            }
        });
    }

    public void zeigInfo6(View v)
    {
        Button allesklarbutton6;
        zeigInfo6.setContentView(R.layout.infopopup_6);
        allesklarbutton6 = (Button) zeigInfo6.findViewById(R.id.allesklarbutton6);

        allesklarbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                zeigInfo6.dismiss();
            }
        });

        zeigInfo6.show();
    }
}
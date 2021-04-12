package com.example.a2study_projektkurs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LernenVokabel_activity extends AppCompatActivity {

    ImageButton backbutton4;
    Dialog zeigInfo5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernen_vokabel_activity);

        backbutton4 = (ImageButton) findViewById(R.id.backbutton4);
        zeigInfo5 = new Dialog(this);

        backbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LernenVokabel_activity.this.finish();
            }
        });
    }

    public void zeigInfo5(View v)
    {
        Button allesklarbutton5;
        zeigInfo5.setContentView(R.layout.infopopup_5);
        allesklarbutton5 = (Button) zeigInfo5.findViewById(R.id.allesklarbutton5);

        allesklarbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                zeigInfo5.dismiss();
            }
        });

        zeigInfo5.show();
    }
}
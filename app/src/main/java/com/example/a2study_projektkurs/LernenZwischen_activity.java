package com.example.a2study_projektkurs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LernenZwischen_activity extends AppCompatActivity {

    Dialog zeigInfo4;
    ImageButton backbutton3;
    Button openKarteikarten;
    Button openVokabellisten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernen_zwischen_activity);

        zeigInfo4 = new Dialog(this);
        backbutton3 = findViewById(R.id.backbutton3);
        openKarteikarten = findViewById(R.id.openKarteikarten);
        openVokabellisten = findViewById(R.id.openVokabelliste);

        backbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LernenZwischen_activity.this.finish();
            }
        });

        openKarteikarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openLernenKartei_Activity();
            }
        });

        openVokabellisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openLernenVokabel_Activity();
            }
        });

    }

    public void openLernenKartei_Activity()
    {
        Intent intent4 = new Intent(this, LernenKartei_Activity.class);
        startActivity(intent4);
    }

    public void openLernenVokabel_Activity()
    {
        Intent intent5 = new Intent(this, LernenVokabel_activity.class);
        startActivity(intent5);
    }


    public void zeigInfo4(View v)
    {
        Button allesklarbutton4;
        zeigInfo4.setContentView(R.layout.infopopup_3);
        allesklarbutton4 = (Button) zeigInfo4.findViewById(R.id.allesklarbutton4);

        allesklarbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                zeigInfo4.dismiss();
            }
        });

        zeigInfo4.show();
    }
}
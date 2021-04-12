package com.example.a2study_projektkurs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton infobutton_1;
    Dialog myDialog1;
    Button lernenbutton;
    Button kursverwaltungbutton;
    Button terminebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lernenbutton = (Button) findViewById(R.id.lernenbutton);
        kursverwaltungbutton = (Button) findViewById(R.id.kursverwaltungbutton);
        terminebutton = (Button) findViewById(R.id.terminebutton);
        infobutton_1 = (ImageButton) findViewById(R.id.infobutton_1);
        myDialog1 =  new Dialog(this);

        lernenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openLernenZwischen_Activity();
            }
        });

        kursverwaltungbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openKursverwaltung_Activity();
            }
        });

        terminebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openTermine_Activity();
            }
        });

    }

    public void openLernenZwischen_Activity()
    {
        Intent intent1 = new Intent(this, LernenZwischen_activity.class);
        startActivity(intent1);
    }

    public void openKursverwaltung_Activity()
    {
        Intent intent2 = new Intent(this, Kursverwaltung_Activity.class);
        startActivity(intent2);
    }

    public void openTermine_Activity()
    {
        Intent intent3 = new Intent(this, Termine_Activity.class);
        startActivity(intent3);
    }

    public void zeigInfo1(View v)
    {

        Button allesklarbutton;
        myDialog1.setContentView(R.layout.infopopup_1);
        allesklarbutton = (Button) myDialog1.findViewById(R.id.allesklarbutton);

        allesklarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog1.dismiss();
            }
        });

        myDialog1.show();

    }

}
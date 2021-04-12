package com.example.a2study_projektkurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Termine_Activity extends AppCompatActivity {

    Dialog myDialog2;
    Dialog addEvent;

    EditText enterTerminBezeichnung;
    ListView lvEvents;

    Calendar c;
    String[] monate = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

    Calendar calendarForSelectedDate;

    String gewaehltesDatum;
    ImageButton backbutton2;

    Button wechselZumVorherigenMonat;
    Button wechselZumNächstenMonat;

    TextView zeigeAktMonatUndJahr;
    String AktMonatUndJahr;
    Calendar calendarForAktMonatUndJahr;

    CompactCalendarView compactcalendar;
    Button addEventButton;

    DatabaseAdapter databaseAdapter;
    DatabaseHelper1 databaseHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termine);

        myDialog2 =  new Dialog(this);
        addEvent = new Dialog(this);

        databaseAdapter = new DatabaseAdapter(this);
        lvEvents = (ListView) findViewById(R.id.lvEvents);

        c = Calendar.getInstance();

        calendarForSelectedDate = Calendar.getInstance();

        backbutton2 = (ImageButton) findViewById(R.id.backbutton2);
        wechselZumVorherigenMonat = (Button) findViewById(R.id.wechselZumVorherigenMonat);
        zeigeAktMonatUndJahr = (TextView) findViewById(R.id.zeigeAktMonatUndJahr);
        wechselZumNächstenMonat = (Button) findViewById(R.id.wechselZumNächstenMonat);
        compactcalendar = (CompactCalendarView) findViewById(R.id.compactcalendar);
        addEventButton = (Button) findViewById(R.id.addEventButton);
        enterTerminBezeichnung = (EditText) addEvent.findViewById(R.id.enterTerminBezeichnung);


        databaseHelper1 = new DatabaseHelper1(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper1.getWritableDatabase();


        compactcalendar.setUseThreeLetterAbbreviation(true);
        compactcalendar.shouldSelectFirstDayOfMonthOnScroll(false);

        calendarForAktMonatUndJahr = Calendar.getInstance(Locale.GERMAN);
        AktMonatUndJahr = monate[calendarForAktMonatUndJahr.get(Calendar.MONTH)] + " " + calendarForAktMonatUndJahr.get(Calendar.YEAR);
        zeigeAktMonatUndJahr.setText(AktMonatUndJahr);

        wechselZumVorherigenMonat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                compactcalendar.scrollLeft();
                c.setTime(compactcalendar.getFirstDayOfCurrentMonth());
                zeigeAktMonatUndJahr.setText(monate[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
            }
        });

        wechselZumNächstenMonat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                compactcalendar.scrollRight();
                c.setTime(compactcalendar.getFirstDayOfCurrentMonth());
                zeigeAktMonatUndJahr.setText(monate[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
            }
        });

        compactcalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked)
            {
                calendarForSelectedDate.setTime(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                c.setTime(firstDayOfNewMonth);
                zeigeAktMonatUndJahr.setText(monate[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
            }
        });

        backbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Termine_Activity.this.finish();
            }
        });

    }




    public void zeigInfo2(View v)
    {

        Button allesklarbutton2;
        myDialog2.setContentView(R.layout.infopopup_2);
        allesklarbutton2 = (Button) myDialog2.findViewById(R.id.allesklarbutton2);

        allesklarbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog2.dismiss();
            }
        });

        myDialog2.show();

    }




    public void zeigAddEventPopup(View v)
    {
        TextView zeigAktDatum;
        Button abbrechenBtn;
        Button terminErstellenBtn;

        addEvent.setContentView(R.layout.addevent_popup);

        zeigAktDatum = (TextView) addEvent.findViewById(R.id.zeigAktDatum);
        abbrechenBtn = (Button) addEvent.findViewById(R.id.abbrechenBtn);
        terminErstellenBtn = (Button) addEvent.findViewById(R.id.terminErstellenBtn);

        zeigAktDatum.setText(calendarForSelectedDate.get(Calendar.DAY_OF_MONTH) + ". " + monate[calendarForSelectedDate.get(Calendar.MONTH)] + " " + calendarForSelectedDate.get(Calendar.YEAR));

        abbrechenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addEvent.dismiss();
            }
        });

        terminErstellenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(enterTerminBezeichnung.getText().toString().isEmpty())
                {
                    Toast errorToast = Toast.makeText(getApplicationContext(), "Keine Terminbezeichnung eingegeben!", Toast.LENGTH_LONG);
                    errorToast.show();
                }
                else
                {
                    String bezeichnungValue = enterTerminBezeichnung.getText().toString();
                    long dateinmilisValue = calendarForSelectedDate.getTimeInMillis();
                    String datumValue = turnDateInMilisIntoDateString(dateinmilisValue);

                    long id = databaseAdapter.insertData(bezeichnungValue, datumValue, dateinmilisValue);

                    if(id < 0)
                    {
                        Toast.makeText(getApplicationContext(), "Hinzufügen fehlgeschlagen", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Hinzufügen erfolgreich", Toast.LENGTH_SHORT).show();
                    }
                }

                addEvent.dismiss();
            }
        });

        addEvent.show();
    }

    public String turnDateInMilisIntoDateString(long DateInMilis)
    {
        String datumAlsString;
        Calendar cTD = Calendar.getInstance();
        cTD.setTime(new Date(DateInMilis));
        int day = cTD.get(Calendar.DAY_OF_MONTH);
        int month = cTD.get(Calendar.MONTH);
        month = month + 1;
        int year = cTD.get(Calendar.YEAR);

        if(day < 10 && month < 10)
        {
            datumAlsString = "0" + day + ".0" + month + "." + year;
        }
        else if(day < 10)
        {
            datumAlsString = "0" + day + "." + month + "." + year;
        }
        else if(month < 10)
        {
            datumAlsString = day + ".0" + month + "." + year;
        }
        else
        {
            datumAlsString = day + "." + month + "." + year;
        }

        return datumAlsString;
    }

}
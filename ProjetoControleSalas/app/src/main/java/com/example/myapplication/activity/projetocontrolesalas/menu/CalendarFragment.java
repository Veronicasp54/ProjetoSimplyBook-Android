package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private TextView textViewDataAtual;
    private HorizontalCalendar horizontalCalendar;
    private View view;
    private TextView mesAtual;
    private Locale local;
    private Date data;
    private SimpleDateFormat dateFormat;
    private ListView listViewEventos;
    private FloatingActionButton floatingActionButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        createCalendar();
        iniciaComponentes();


        return view;
    }

    private void createCalendar() {

        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .textSize(14f, 24f, 14f)
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.GRAY, Color.BLACK)
                .end()
                .build();

        eventCalendar();

    }

    private void eventCalendar() {
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), DateFormat.format("EEE, MMM d, yyyy", date) + " is selected!", Toast.LENGTH_SHORT).show();


            }

        });
    }

    private void iniciaComponentes() {

        data = new Date();
        local = new Locale("pt", "BR");

        listViewEventos = view.findViewById(R.id.lista_eventos_listview);

        textViewDataAtual = (TextView) view.findViewById(R.id.diaDaSemana);
        textViewDataAtual.setText(getData());

        mesAtual = (TextView) view.findViewById(R.id.mesAtual);
        mesAtual.setText(getMesAtual().toString().toUpperCase());

        floatingActionButton = view.findViewById(R.id.floatActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "floatingActionButton", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private String getData() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd',' EEEE", local);
        return dateFormat.format(data);
    }

    private CharSequence getMesAtual() {

        dateFormat = new SimpleDateFormat("MMMM", local);
        return dateFormat.format(data);
    }


}
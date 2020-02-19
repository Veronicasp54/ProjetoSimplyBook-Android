package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestEmpresa;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;
import com.example.myapplication.activity.projetocontrolesalas.ui.Cadastro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    private Spinner spinnerSalas;

    private List<Empresa> listaSalas = new ArrayList();
    private List<String> listaNomesSalas = new ArrayList<>();
    private CharSequence dataSelecionada;


    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);


        createCalendar();
        iniciaComponentes();

        return view;
    }

    private void createCalendar() {

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        //avancando um dia

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
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
        dataSelecionada = DateFormat.format("dd/MM/yyyy", Calendar.getInstance().getTime());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), DateFormat.format("EEE, MMM d, yyyy", date) + " is selected!", Toast.LENGTH_SHORT).show();
                dataSelecionada = DateFormat.format("dd/MM/yyyy", date);

                Toast.makeText(getContext(), dataSelecionada + " is selected!", Toast.LENGTH_SHORT).show();


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

        adicionarReuniao();


    }

    private void adicionarReuniao() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityReuniao.class);
//                Bundle dados = new Bundle();

                intent.putExtra("dataSelecionada", dataSelecionada);
                //intent.putExtras(dados);

                startActivity(intent);

            }
        });
    }

    private void startClass(Class classe) {
        Intent intent = new Intent(getActivity(), classe);
        startActivity(intent);
    }

    private String getData() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd',' EEEE", local);

        //SimpleDateFormat dateFormatReserva = new SimpleDateFormat("dd/MM/yyyy",local);
        //String dataSelecionada = dateFormatReserva.format(data);

        // Toast.makeText(getContext(), dateFormatReserva.format(data), Toast.LENGTH_LONG).show();

        return dateFormat.format(data);
    }

    private CharSequence getMesAtual() {
        dateFormat = new SimpleDateFormat("MMMM", local);
        return dateFormat.format(data);
    }

    private void buscarListSalas() {
        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        String requestSalas = null;

        try {
            requestSalas = new RequestSalas().execute(preferences.getString("userIdEmpresa", null)).get();
            System.out.println("salas em string: " + requestSalas);


            if (requestSalas.length() > 0) {

                JSONArray jsonArray = new JSONArray(requestSalas);

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.has("nome")) {

                            String nome = jsonObject.getString("nome");

                            Empresa newEmpresa = new Empresa();

                            newEmpresa.setNomeEmpresa(nome);

                            listaNomesSalas.add(newEmpresa.getNomeEmpresa());

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaNomesSalas);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerSalas.setAdapter(adapter);
                            spinnerSalas.setVisibility(View.VISIBLE);

                        }


                    }


                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createSpinner() {
        spinnerSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //idSalaSelecionada = listaSalas.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showDialogDetalhesSala() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dialogLayout = inflater.inflate(R.layout.box_reservar, null);
        builder.setView(dialogLayout);

        spinnerSalas = dialogLayout.findViewById(R.id.spinnerSalas);

        createSpinner();
        buscarListSalas();

        final AlertDialog dialog = builder.create();

        dialog.show();
    }


}
package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.adapter.AdapterReservasAll;
import com.example.myapplication.activity.projetocontrolesalas.adapter.AdapterReservasUser;
import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.example.myapplication.activity.projetocontrolesalas.model.ReservaSala;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestEmpresa;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestExibirReservas;
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
    private List<ReservaSala> reservas = new ArrayList<>();
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
        endDate.add(Calendar.MONTH, 1);

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


        textViewDataAtual = (TextView) view.findViewById(R.id.diaDaSemana);
        textViewDataAtual.setText(getData());

        mesAtual = (TextView) view.findViewById(R.id.mesAtual);
        mesAtual.setText(getMesAtual().toString().toUpperCase());

        floatingActionButton = view.findViewById(R.id.floatActionButton);

        adicionarReuniao();
        exibirTodasReservas();


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

    private void exibirTodasReservas() {

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);
        System.out.println(preferences.getString("userId", null));

        String requestReservas = null;
        try {
            requestReservas = new RequestExibirReservas().execute(preferences.getString("userId", null)).get();

            System.out.println(requestReservas);

            JSONArray reservasJson = new JSONArray(requestReservas);

            if (reservasJson.length() > 0) {

                for (int i = 0; i < reservasJson.length(); i++) {

                    JSONObject jsonObjectReserva = reservasJson.getJSONObject(i);

                    if (jsonObjectReserva.has("idUsuario") && jsonObjectReserva.has("id")) {

                        //I/System.out: [{"ativo":true,"dataAlteracao":"2020-02-19T17:00:15Z[UTC]","dataCriacao":"2020-02-19T17:00:15Z[UTC]","dataHoraFim":"2020-02-19T20:00:00Z[UTC]","dataHoraInicio":"2020-02-19T21:00:00Z[UTC]","descricao":"reserva","id":1,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:01:54Z[UTC]","dataCriacao":"2020-02-19T17:01:54Z[UTC]","dataHoraFim":"2020-02-19T21:01:00Z[UTC]","dataHoraInicio":"2020-02-19T22:01:00Z[UTC]","descricao":"reserva","id":2,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:04:09Z[UTC]","dataCriacao":"2020-02-19T17:04:09Z[UTC]","dataHoraFim":"2020-02-19T20:04:00Z[UTC]","dataHoraInicio":"2020-02-19T21:04:00Z[UTC]","descricao":"re","id":3,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:07:15Z[UTC]","dataCriacao":"2020-02-19T17:07:15Z[UTC]","dataHoraFim":"2020-02-19T20:07:00Z[UTC]","dataHoraInicio":"2020-02-19T21:07:00Z[UTC]","descricao":"reservar","id":4,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-20T14:16:57Z[UTC]","dataCriacao":"2020-02-20T14:16:57Z[UTC]","dataHoraFim":"2020-02-20T17:16:00Z[UTC]","dataHoraInicio":"2020-02-20T17:30:00Z[UTC]","descricao":"desc","id":5,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"}]
                        int idUser = jsonObjectReserva.getInt("idUsuario");
                        int idSala = jsonObjectReserva.getInt("idSala");
                        int idReserva = jsonObjectReserva.getInt("id");
                        String nomeOrganizador = jsonObjectReserva.getString("nomeOrganizador");
                        String descricaoReserva = jsonObjectReserva.getString("descricao");
//                        String dataHoraInicio = jsonObjectReserva.getString("data_hora_inicio");
         //               String dataHoraFim = jsonObjectReserva.getString("data_hora_fim");

                        ReservaSala newReserva = new ReservaSala();

                        newReserva.setIdSala(idSala);
                        newReserva.setDescricaoReserva(descricaoReserva);
                        newReserva.setIdUser(idUser);
                        newReserva.setNomeOrganizador(nomeOrganizador);

                        newReserva.setNomeSala("Sala para reuniao");
                        newReserva.setDataReserva("data");
                      //  newReserva.setHorarioInicio(dataHoraInicio);
                     //   newReserva.setHorarioFinal(dataHoraFim);

                        // newReserva.setNomeSala(idSala);

                        reservas.add(newReserva);

                    }

                }
                listViewEventos = view.findViewById(R.id.lista_eventos_listview);

                AdapterReservasAll adapter = new AdapterReservasAll(reservas, getActivity());
                listViewEventos.setAdapter(adapter);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
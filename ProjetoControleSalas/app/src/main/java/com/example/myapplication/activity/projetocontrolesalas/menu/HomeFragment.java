package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;

public class HomeFragment extends Fragment {

    private View view;
    private TextView textNomeEmpresa;
    private ListView listSalas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);





        return view;

    }

}

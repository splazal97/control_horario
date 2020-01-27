package com.example.control_horario.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class FicharFragment extends Fragment  {
    MainViewModel mainViewModel;
    Button finalJornada;
    Button iniciarJornada;
    public FicharFragment() {
        // Required empty public constructor
    }
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fichar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);



        iniciarJornada = view.findViewById(R.id.iniciarJornadaBTN);
        finalJornada = view.findViewById(R.id.finalizarJornadaBTN);
        iniciarJornada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (i == 1) {
                            Toasty.error(getContext(),"Para iniciar jornada debes realizar un doble click",Toast.LENGTH_SHORT, true).show();
                        } else  if (i ==2){
                            Toasty.success(getContext(),"Horas añadidas correctamente",Toast.LENGTH_SHORT, true).show();
                            mainViewModel.inicarJornada();
                        }
                        i= 0;
                    }
                },500);

            }
        });
        finalJornada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            Toasty.error(getContext(),"Para finalizar jornada debes realizar un doble click",Toast.LENGTH_SHORT, true).show();
                        }else if (i ==2){
                            Toasty.success(getContext(),"Horas añadidas correctamente",Toast.LENGTH_SHORT, true).show();
                            mainViewModel.finalJornada();
                        }
                        i=0;
                    }
                },500);
            }
        });

        }



}

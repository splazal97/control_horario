package com.example.control_horario.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;
import com.example.control_horario.model.Empleado;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioAdminFragment extends Fragment {

    MainViewModel mainViewModel;
    TextView inicioAdminText;
    public InicioAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_admin, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        inicioAdminText = view.findViewById(R.id.inicioAdminText);
        mainViewModel.empleadoLogueado.observe(getViewLifecycleOwner(), new Observer<Empleado>() {
            @Override
            public void onChanged(Empleado empleado) {
                if(empleado != null){
                    inicioAdminText.setText(empleado.nombreCompleto);
                }else {
                    Log.e("ABCD", "mierda no deberia ser null");
                }
            }
        });

    }
}

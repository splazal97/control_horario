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
public class InicioFragment extends Fragment {


    private TextView userTextView;
    private MainViewModel mainViewModel;

    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        userTextView = view.findViewById(R.id.edittext_username_inicio);
        mainViewModel.empleadoLogueado.observe(getViewLifecycleOwner(), new Observer<Empleado>() {
            @Override
            public void onChanged(Empleado empleado) {
                if(empleado != null){
                    userTextView.setText(empleado.nombreCompleto);
                }else {
                    Log.e("ABCD", "mierda no deberia ser null");
                }
            }
        });

    }
}

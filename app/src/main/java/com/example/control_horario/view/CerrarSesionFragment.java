package com.example.control_horario.view;


import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CerrarSesionFragment extends Fragment {

    private  MainViewModel mainViewModel;

    public CerrarSesionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel.cerrarSesion();
        Navigation.findNavController(view).navigate(R.id.iniciarSesionFragment);
    }
}

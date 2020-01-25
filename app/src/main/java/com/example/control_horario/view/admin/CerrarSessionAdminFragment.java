package com.example.control_horario.view.admin;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.control_horario.MainActivity;
import com.example.control_horario.MainActivityAdmin;
import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;
import com.example.control_horario.view.MyFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CerrarSessionAdminFragment extends Fragment {

    MainViewModel mainViewModel;
    public CerrarSessionAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cerrar_session_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        mainViewModel.cerrarSesion();

        mainViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<MainViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(MainViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                Intent MainActivity = new Intent(requireActivity(), MainActivity.class);
                startActivity(MainActivity);
            }
        });
    }
}

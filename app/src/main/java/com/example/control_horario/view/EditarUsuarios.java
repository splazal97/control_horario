package com.example.control_horario.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.control_horario.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarUsuarios extends Fragment {


    public EditarUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_usuarios, container, false);
    }

}

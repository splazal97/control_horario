package com.example.control_horario.view.admin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_horario.R;
import com.example.control_horario.model.Empleado;
import com.example.control_horario.view.MyFragment;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarEmpleadoFragment extends MyFragment {

    EditText nombre, usuario, contrasenya;
    Button buttonGuardar;
    int id;


    public EditarEmpleadoFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_empleado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre = view.findViewById(R.id.textViewNombreEdit);
        usuario = view.findViewById(R.id.textViewUserEdit);
        contrasenya = view.findViewById(R.id.textViewPassEdit);
        buttonGuardar = view.findViewById(R.id.ButtonGuardar);


        mainViewModel.empleadoSeleccionado.observe(getViewLifecycleOwner(), new Observer<Empleado>() {
            @Override
            public void onChanged(Empleado empleado) {
                nombre.setText(empleado.nombreCompleto);
                usuario.setText(empleado.username);
                contrasenya.setText(empleado.contrasenya);
                id=empleado.id;
            }
        });


        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.guardarCambios(nombre.getText().toString(),usuario.getText().toString(),contrasenya.getText().toString(),id);
                Toasty.success(getContext(),"Cambios realizados", Toast.LENGTH_SHORT,true).show();
                navController.navigate(R.id.editarUsuarios);
            }
        });

    }

    void mostrarEmpleado(Empleado empleado){

    }
}

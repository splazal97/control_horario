package com.example.control_horario.view;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_horario.MainActivityAdmin;
import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class IniciarSesionFragment extends Fragment {


    private MainViewModel mainViewModel;

    private EditText  usuarioEditText;
    private EditText contrasenyaEditText;
    private Button iniciarSesionButton;
    private TextView irAlRegistroTextView;


    public IniciarSesionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        usuarioEditText = view.findViewById(R.id.insertUser);
        contrasenyaEditText = view.findViewById(R.id.insertPass);
        iniciarSesionButton = view.findViewById(R.id.iniciar_sesionBtn);
        irAlRegistroTextView = view.findViewById(R.id.registerText);



        irAlRegistroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registerFragment);
            }
        });

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (usuarioEditText.getText().toString().isEmpty()) {
                    contrasenyaEditText.setError("Insertar usuario");
                    Toasty.error(getContext(),"Inserte el nombre de usuario",Toast.LENGTH_SHORT,true).show();
                } else if (contrasenyaEditText.getText().toString().isEmpty()){
                    contrasenyaEditText.setError("Insertar contraseña");
                    Toasty.error(getContext(),"Inserte la contraseña",Toast.LENGTH_SHORT,true).show();

                } else {
                    mainViewModel.iniciarSesion(usuarioEditText.getText().toString(), contrasenyaEditText.getText().toString());
                }

            }
        });

        mainViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<MainViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(MainViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).navigate(R.id.inicioFragment);
                        break;

                    case ADMIN:
                        Intent MainActivitiAdmin = new Intent(requireActivity(), MainActivityAdmin.class);
                        startActivity(MainActivitiAdmin);

                        break;
                    case AUTENTICACION_INVALIDA:
                        Toasty.error(getContext(), "CREDENCIALES NO VALIDAS", Toast.LENGTH_SHORT,true).show();
                        break;
                }
            }
        });
    }
}

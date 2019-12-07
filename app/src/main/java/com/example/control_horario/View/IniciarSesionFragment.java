package com.example.control_horario.View;


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

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IniciarSesionFragment extends Fragment {


    private MainViewModel mainViewModel;

    private EditText usuarioEditText, contrasenyaEditText;
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
                mainViewModel.iniciarSesion(usuarioEditText.getText().toString(), contrasenyaEditText.getText().toString());
            }
        });

        mainViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<MainViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(MainViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).popBackStack();
                        break;

                    case AUTENTICACION_INVALIDA:
                        Toast.makeText(getContext(), "CREDENCIALES NO VALIDAS", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}

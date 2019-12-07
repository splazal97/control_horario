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
import android.widget.Toast;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private MainViewModel mainViewModel;

    private EditText nombreEditText, contrasenyaEditText, biografiaEditText;
    private Button registrarButton;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        nombreEditText = view.findViewById(R.id.edittext_nombre);
        contrasenyaEditText = view.findViewById(R.id.edittext_contrasenya);
        biografiaEditText = view.findViewById(R.id.edittext_biografia);
        registrarButton = view.findViewById(R.id.button_registrar);

        // Poner el estado del registro al estado INICIAR,
        // (por si se habia quedado en COMPLETADO o NOMBRE_NO_DISPONIBLE)
        mainViewModel.iniciarRegistro();

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.crearCuentaEIniciarSesion(nombreEditText.getText().toString(), contrasenyaEditText.getText().toString(), biografiaEditText.getText().toString());
            }
        });

        mainViewModel.estadoDelRegistro.observe(getViewLifecycleOwner(), new Observer<MainViewModel.EstadoDelRegistro>() {
            @Override
            public void onChanged(MainViewModel.EstadoDelRegistro estadoDelRegistro) {
                switch (estadoDelRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                        Toast.makeText(getContext(), "NOMBRE DE USUARIO NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        mainViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<MainViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(MainViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).popBackStack();
                        break;
                }
            }
        });
    }
}

package com.example.control_horario.view.admin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;
import com.example.control_horario.model.Empleado;
import com.example.control_horario.view.MyFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerEmpleados extends MyFragment {
    MainViewModel mainViewModel;

    private VerEmpleados.verEmpleadosAdapter verEmpleadosAdapter;

    public VerEmpleados() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ver_empleados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_empleados);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        verEmpleadosAdapter = new verEmpleadosAdapter();
        recyclerView.setAdapter(verEmpleadosAdapter);

        mainViewModel.verEmpleados().observe(this, new Observer<List<Empleado>>() {
            @Override
            public void onChanged(List<Empleado> queryResult) {
                verEmpleadosAdapter.establcerEmpleados(queryResult);
            }
        });



    }

    class verEmpleadosAdapter extends RecyclerView.Adapter<verEmpleadosAdapter.verEmpleadosViewHolder> {

        List<Empleado> verEmpleadosDealle;

        @NonNull
        @Override
        public verEmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new verEmpleadosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_empleados, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull verEmpleadosViewHolder holder, int position) {
            final Empleado empleado = verEmpleadosDealle.get(position);
            holder.nombreTextView.setText("Nombre: "+empleado.nombreCompleto);
            holder.usuarioTextView.setText("Usuario: "+empleado.username);

            holder.editarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainViewModel.empleadoSeleccionado.postValue(empleado);
                    navController.navigate(R.id.editarEmpleadoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return verEmpleadosDealle !=null ? verEmpleadosDealle.size() : 0;
        }
        void establcerEmpleados(List<Empleado> list){
            verEmpleadosDealle = list;
            notifyDataSetChanged();

        }


        class verEmpleadosViewHolder extends RecyclerView.ViewHolder {
            TextView usuarioTextView, nombreTextView;
            ImageView editarUsuario;

            public verEmpleadosViewHolder(@NonNull View itemView) {
                super(itemView);
                usuarioTextView = itemView.findViewById(R.id.usuarioTextView);
                nombreTextView = itemView.findViewById(R.id.nombreTextView);
                editarUsuario = itemView.findViewById(R.id.editUser);

            }
        }
    }
}

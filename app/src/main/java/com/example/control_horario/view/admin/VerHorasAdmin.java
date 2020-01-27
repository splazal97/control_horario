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
import android.widget.TextView;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;
import com.example.control_horario.model.EmpleadosHoras;

import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerHorasAdmin extends Fragment {
    MainViewModel mainViewModel;
    private VerHorasAdmin.verHorasAdminAdapter verHorasAdminAdapter;


    public VerHorasAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_horas_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_ver_horas_admin);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        verHorasAdminAdapter = new verHorasAdminAdapter();
        recyclerView.setAdapter(verHorasAdminAdapter);


        mainViewModel.verEmpleadosHoras().observe(this, new Observer<List<EmpleadosHoras>>() {
            @Override
            public void onChanged(List<EmpleadosHoras> queryresult) {
                verHorasAdminAdapter.establecerEmpladosHoras(queryresult);
            }
        });


    }
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    class verHorasAdminAdapter extends RecyclerView.Adapter<VerHorasAdmin.verHorasAdminAdapter.VerHorasAdminViewHolder> {

        List<EmpleadosHoras> empleadosHorasDetalles;

        @NonNull
        @Override
        public VerHorasAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VerHorasAdminViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_verhoras_admin,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull VerHorasAdminViewHolder holder, int position) {
            final  EmpleadosHoras empleadosHoras = empleadosHorasDetalles.get(position);

            if (empleadosHoras.inicio != null){
                holder.nombre.setText("Nombre: "+empleadosHoras.nombreCompleto);
                holder.usuario.setText("  Usuario: "+empleadosHoras.username);
                holder.horasinsertadas.setText("Inicio: ");
                holder.jornadas.setText(empleadosHoras.inicio.format(formatter));

            }
            if (empleadosHoras.fin !=null){
                holder.nombre.setText("Nombre : "+ empleadosHoras.nombreCompleto);
                holder.usuario.setText("  Usuario: " +empleadosHoras.username);
                holder.horasinsertadas.setText("Fin: ");
                holder.jornadas.setText(empleadosHoras.fin.format(formatter));

            }
        }

        @Override
        public int getItemCount() {
            return empleadosHorasDetalles !=null ? empleadosHorasDetalles.size() : 0;
        }
        void establecerEmpladosHoras(List<EmpleadosHoras> list){
            empleadosHorasDetalles =list;
            notifyDataSetChanged();
        }

        class VerHorasAdminViewHolder extends RecyclerView.ViewHolder {
            TextView nombre,usuario, horasinsertadas, jornadas;
            public VerHorasAdminViewHolder(@NonNull View itemView) {
                super(itemView);
                nombre = itemView.findViewById(R.id.textViewNombreAdmin);
                usuario = itemView.findViewById(R.id.textViewUsuarioAdmin);
                horasinsertadas = itemView.findViewById(R.id.textViewHorasInsertadas_admin);
                jornadas = itemView.findViewById(R.id.textViewHorasJornadaAdmin);
            }
        }
    }
}

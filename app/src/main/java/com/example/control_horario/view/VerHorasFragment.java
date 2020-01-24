package com.example.control_horario.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;
import androidx.room.TypeConverter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.control_horario.MainViewModel;
import com.example.control_horario.R;
import com.example.control_horario.model.Converters;
import com.example.control_horario.model.Horas;

import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerHorasFragment extends Fragment {

    MainViewModel mainViewModel;
    private VerHorasAdapter verHorasAdapter;

    public VerHorasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_horas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_listaTareas);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        verHorasAdapter = new VerHorasAdapter();
        recyclerView.setAdapter(verHorasAdapter);

        mainViewModel.verHoras().observe(this, new Observer<List<Horas>>() {
            @Override
            public void onChanged(List<Horas> queryResult) {
                for(Horas h : queryResult){
                    Log.e("ABCD", "HORASSSS " + h.toString());
                }
                verHorasAdapter.establecerHoras(queryResult);

            }
        });
    }
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


    class VerHorasAdapter extends RecyclerView.Adapter<VerHorasAdapter.VerHorasViewHolder>{

        List<Horas> verHorasDetalle;

        @NonNull
        @Override
        public VerHorasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VerHorasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_verhoras, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VerHorasAdapter.VerHorasViewHolder holder, int position) {
            final Horas horas = verHorasDetalle.get(position);

            if (horas.inicio != null){
                holder.iniciarJornada.setText(horas.inicio.format(formatter));
            } else {
                holder.iniciarJornada.setText("Hora de inicio");
            }

             if (horas.fin != null) {
                 holder.finalJornada.setText(horas.fin.format(formatter));
             } else {
                 holder.finalJornada.setText("Hora de fin");
             }

        }

        @Override
        public int getItemCount() {
            return verHorasDetalle != null ? verHorasDetalle.size() : 0;
        }


        void establecerHoras(List<Horas> list){
            verHorasDetalle = list;
            notifyDataSetChanged();
        }

        class VerHorasViewHolder extends  RecyclerView.ViewHolder{
            TextView iniciarJornada, finalJornada;

            public VerHorasViewHolder(@NonNull View itemView) {
                super(itemView);
                iniciarJornada= itemView.findViewById(R.id.InicioJornadaText);
                finalJornada = itemView.findViewById(R.id.FinJornadaText);
            }
        }
    }

}


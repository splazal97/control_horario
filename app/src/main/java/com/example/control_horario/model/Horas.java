package com.example.control_horario.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(foreignKeys = @ForeignKey(entity = Empleado.class,
                                    parentColumns = "id",
                                    childColumns = "idEmpleado"))

public class Horas {
    @PrimaryKey(autoGenerate = true)
    public int id;


    public int idEmpleado;
    public LocalDateTime inicio;
    public LocalDateTime fin;



    public Horas(int idEmpleado, LocalDateTime inicio) {
        this.idEmpleado = idEmpleado;
        this.inicio = inicio;
    }

    @Override
    public String toString() {
        return "Horas{" +
                "id=" + id +
                ", idEmpleado=" + idEmpleado +
                ", inicio=" + inicio +
                ", fin=" + fin +
                '}';
    }

}



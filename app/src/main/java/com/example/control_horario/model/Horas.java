package com.example.control_horario.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(foreignKeys = @ForeignKey(entity = Empleado.class,
                                    parentColumns = "id",
                                    childColumns = "idEmpleado"))

public class Horas {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int idEmpleado;
    public String inicio;
    public String fin;

}



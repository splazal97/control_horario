package com.example.control_horario.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Empleado.class,
                                    parentColumns = "id",
                                    childColumns = "idEmpleado"))

public class Horas {
    @PrimaryKey(autoGenerate = true)
    int id;
    int idEmpleado;
    String inicio;
    String fin;

}

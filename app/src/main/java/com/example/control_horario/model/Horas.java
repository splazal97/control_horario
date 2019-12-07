package com.example.control_horario.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Empleado.class,
                                    parentColumns = "id",
                                    childColumns = "idEmpleado"))

public class Horas {
    int idEmpleado;

    @PrimaryKey(autoGenerate = true)
    int id;
    String inicio;
    String fin;

    // 19/10/2011 23:20
    // 20/10/2011 3:30
}

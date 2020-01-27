package com.example.control_horario.model;

import androidx.room.DatabaseView;

import java.time.LocalDateTime;

@DatabaseView("SELECT e.id, e.nombreCompleto,e.username, h.inicio, h.fin FROM Empleado e JOIN Horas h on e.id=h.idEmpleado")

public class EmpleadosHoras {
    public int id;
    public String nombreCompleto;
    public String username;
    public LocalDateTime inicio;
    public LocalDateTime fin;
/*
    public EmpleadosHoras(int id) {
        this.id = id;
    }

 */
}

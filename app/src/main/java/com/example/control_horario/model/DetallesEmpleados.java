package com.example.control_horario.model;


import androidx.room.DatabaseView;

@DatabaseView("SELECT Empleado.id, Empleado.nombreCompleto, Empleado.username,Empleado.contrasenya FROM Empleado")
public class DetallesEmpleados {
    public  int id;
    public  String nombre;
    public String usuario;
    public String contraseña;


}

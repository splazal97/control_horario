package com.example.control_horario.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Empleado {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombreCompleto;
    public String username;
    public String contrasenya;

    public Empleado(String nombreCompleto, String username, String contrasenya){
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", username='" + username + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}

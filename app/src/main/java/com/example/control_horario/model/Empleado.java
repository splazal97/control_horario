package com.example.control_horario.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Empleado {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;
    public String username;
    public String contrasenya;
    public String aplelidos;

    public Empleado(String nombre, String username, String contrasenya){
        this.nombre = nombre;
        this.username = username;
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", aplelidos='" + aplelidos + '\'' +
                '}';
    }
}

package com.example.control_horario.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.control_horario.model.Empleado;
import com.example.control_horario.model.Empleado;
import com.example.control_horario.model.Horas;

import java.util.List;

@Dao
public abstract class FicharDAO {

    @Insert
    public abstract void insertarEmpleado(Empleado empleado);

    @Query("SELECT * FROM Empleado")
    public abstract LiveData<List<Empleado>> getEmpleados();

    @Query("SELECT * FROM Horas")
    public  abstract  LiveData<List<Horas>> getHoras();

    @Query("SELECT * FROM Empleado WHERE username = :username AND contrasenya = :contrasenya")
    public abstract Empleado autenticar(String username, String contrasenya);

    @Query("SELECT * FROM Empleado WHERE username = :username")
    public abstract Empleado comprobarNombreDisponible(String username);

    @Query("INSERT INTO Horas(idEmpleado, inicio) VALUES (:idEmpleado, date('now'))")
    public abstract Horas iniciarJornada(Horas idEmpleado);
/*
    @Query("INSERT INTO Horas (idEmpleado, fin) VALUES (:idEmpleado, date('now'))")
    public abstract Horas finalJornada(int idEmpleado);

 */

}


// Empleado empleado = new Empleado("Miguel", "Hernandez");
// INSERT INTO EMPLEADO VALUES(empleado.nombreCompleto, empleado.apellido)


// Empleado empleado = new Empleado("Miguel", "Hernandez");
// dao.insertarEmpleado(empleado);
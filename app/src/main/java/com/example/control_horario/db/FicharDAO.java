package com.example.control_horario.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.control_horario.model.Empleado;
import com.example.control_horario.model.Horas;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("INSERT INTO Horas(idEmpleado, inicio) VALUES (:idEmpleado, :inicio)")
    public abstract long iniciarJornada(int idEmpleado, LocalDateTime inicio);


    @Query("INSERT INTO Horas(idEmpleado, fin) VALUES (:idEmpleado, :fin)")
    public abstract long finalJornada(int idEmpleado, LocalDateTime fin);


    @Query("SELECT * FROM Horas WHERE idEmpleado = :idEmpleado")
    public  abstract LiveData<List<Horas>> verHoras(int idEmpleado);

}


// Empleado empleado = new Empleado("Miguel", "Hernandez");
// INSERT INTO EMPLEADO VALUES(empleado.nombreCompleto, empleado.apellido)


// Empleado empleado = new Empleado("Miguel", "Hernandez");
// dao.insertarEmpleado(empleado);
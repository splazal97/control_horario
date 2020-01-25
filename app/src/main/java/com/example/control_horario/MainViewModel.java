package com.example.control_horario;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.control_horario.db.FicharDAO;
import com.example.control_horario.db.FicharDB;
import com.example.control_horario.model.Empleado;
import com.example.control_horario.model.Horas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainViewModel extends AndroidViewModel {

    public enum EstadoDeLaAutenticacion {
        NO_AUTENTICADO,
        AUTENTICADO,
        AUTENTICACION_INVALIDA,
        ADMIN

    }

    public enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO,
        DATOS_NO_VALIDOS
    }

    FicharDAO dao;

    public MutableLiveData<Empleado> empleadoLogueado = new MutableLiveData<>();
    public MutableLiveData<EstadoDeLaAutenticacion> estadoDeLaAutenticacion = new MutableLiveData<>(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>(EstadoDelRegistro.INICIO_DEL_REGISTRO);


    int idEmpleado = -1;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dao = FicharDB.getInstance(application).dao();

    }
    void insertarEmpleado(final Empleado empleado) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertarEmpleado(empleado);
            }
        });
    }

    LiveData<List<Empleado>> obtenerEmpleados() {
        return dao.getEmpleados();
    }
    LiveData<List<Horas>> obtenerHoras(){
        return dao.getHoras();
    }

    public LiveData<List<Horas>> verHoras(){
        return dao.verHoras(idEmpleado);
    }
    public  LiveData<List<Empleado>> verEmpleados(){
        return dao.getEmpleados();
    }


    public void iniciarRegistro() {
        estadoDelRegistro.postValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    }

    public void crearCuentaEIniciarSesion(final String username, final String contrasenya, final String nombreCompleto) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Empleado empleado = dao.comprobarNombreDisponible(username);
                if (empleado == null) {
                    if (nombreCompleto.isEmpty()) {
                        estadoDelRegistro.postValue(EstadoDelRegistro.DATOS_NO_VALIDOS);
                    } else if (username.isEmpty()) {
                        estadoDelRegistro.postValue(EstadoDelRegistro.DATOS_NO_VALIDOS);
                    } else if (contrasenya.isEmpty()) {
                        estadoDelRegistro.postValue(EstadoDelRegistro.DATOS_NO_VALIDOS);
                    } else {
                        dao.insertarEmpleado(new Empleado(nombreCompleto, username, contrasenya));
                        estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                        iniciarSesion(username, contrasenya);
                    }
                } else {
                    estadoDelRegistro.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }
            }
        });
    }

    public void iniciarSesion(final String nombre, final String contrasenya) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Empleado empleado = dao.autenticar(nombre, contrasenya);
                    if (empleado.username.equals("admin")) {
                        estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.ADMIN);

                    } else if (empleado != null) {
                        idEmpleado = empleado.id;

                        empleadoLogueado.postValue(empleado);
                        estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICADO);

                    } else {
                        estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICACION_INVALIDA);
                    }
                }

        });
    }

    public void cerrarSesion() {
        empleadoLogueado.postValue(null);
        estadoDeLaAutenticacion.setValue(EstadoDeLaAutenticacion.NO_AUTENTICADO);

    }

    public void inicarJornada() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.iniciarJornada(idEmpleado, LocalDateTime.now());
            }
        });
    }

    public  void finalJornada(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.finalJornada(idEmpleado, LocalDateTime.now());
            }
        });
    }



}

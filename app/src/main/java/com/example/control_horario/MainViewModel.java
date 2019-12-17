package com.example.control_horario;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.control_horario.db.FicharDAO;
import com.example.control_horario.db.FicharDB;
import com.example.control_horario.model.Empleado;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public enum EstadoDeLaAutenticacion {
        NO_AUTENTICADO,
        AUTENTICADO,
        AUTENTICACION_INVALIDA
    }

    public enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO,
        DATOS_NO_VALIDOS
    }

    FicharDAO dao;

    public MutableLiveData<Empleado> usuarioLogeado = new MutableLiveData<>();

    public MutableLiveData<EstadoDeLaAutenticacion> estadoDeLaAutenticacion = new MutableLiveData<>(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>(EstadoDelRegistro.INICIO_DEL_REGISTRO);


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


    public void iniciarRegistro() {
        estadoDelRegistro.postValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    }

    public void crearCuentaEIniciarSesion(final String username, final String contrasenya, final String nombreCompleto) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Empleado usuario = dao.comprobarNombreDisponible(username);
                if (usuario == null) {
                    if (nombreCompleto.isEmpty()){
                        estadoDelRegistro.postValue(EstadoDelRegistro.DATOS_NO_VALIDOS);
                    } else if (username.isEmpty()){
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
                Empleado usuario = dao.autenticar(nombre, contrasenya);
                if (usuario != null) {
                    usuarioLogeado.postValue(usuario);
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICADO);
                } else {
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICACION_INVALIDA);
                }
            }
        });
    }

    public void cerrarSesion() {
        usuarioLogeado.postValue(null);
        estadoDeLaAutenticacion.setValue(EstadoDeLaAutenticacion.NO_AUTENTICADO);

    }


}
package com.example.control_horario.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.control_horario.model.Converters;
import com.example.control_horario.model.DetallesEmpleados;
import com.example.control_horario.model.Empleado;
import com.example.control_horario.model.Horas;


//Cuando se realice un cambio en el esquema de la base de datos hay que cambiar la version
@Database(entities = {Empleado.class, Horas.class}, views = {DetallesEmpleados.class}, version = 7,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class FicharDB extends RoomDatabase {
    private static FicharDB ficharDB;

    public abstract FicharDAO dao();

    public static FicharDB getInstance(final Context context){

        if(ficharDB == null) {
            synchronized (FicharDB.class) {
                ficharDB = Room.databaseBuilder(context, FicharDB.class, "fichar.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                insertarDatosIniciales(getInstance(context).dao());
                            }
                        })
                        .build();
            }
        }

        return ficharDB;
    }

    static void insertarDatosIniciales(final FicharDAO dao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertarEmpleado(new Empleado("Admin", "admin", "admin"));
                dao.insertarEmpleado(new Empleado("a","a","a"));
                dao.insertarEmpleado(new Empleado("sergio","splaza","splaza"));
                dao.insertarEmpleado(new Empleado("cesar","cesar","cesar"));
                dao.insertarEmpleado(new Empleado("david","david","david"));
                dao.insertarEmpleado(new Empleado("dani","dani","dani"));
                dao.insertarEmpleado(new Empleado("adri","adri","adri"));
                dao.insertarEmpleado(new Empleado("montilla","montilla","montilla"));
                dao.insertarEmpleado(new Empleado("javi","javi","javi"));
            }
        });
    }
}

package com.example.control_horario;

import android.os.Bundle;

import com.example.control_horario.model.Empleado;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.inicioFragment,R.id.ficharFragment, R.id.verHorasFragment
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.iniciarSesionFragment) {
                    toolbar.setVisibility(View.GONE);
                    navigationView.setVisibility(View.GONE);
                } else if(destination.getId() == R.id.registerFragment) {
                    toolbar.setVisibility(View.GONE);
                    navigationView.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    navigationView.setVisibility(View.VISIBLE);
                }
            }
        });


        //Aqui escribo yo juju

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

//        EditText nombreEditText = findViewById(R.id.nombre);
//        String nombre = nombreEditText.getText().toString();

        //final Empleado empleado = new Empleado("Miguel Hernandez", "mherna", "mherna");
        //final Empleado empleado1 = new Empleado("sergio","splaza","splaza");


        //mainViewModel.insertarEmpleado(empleado);
        //mainViewModel.insertarEmpleado(empleado1);


        mainViewModel.obtenerEmpleados().observe(this, new Observer<List<Empleado>>() {
            @Override
            public void onChanged(List<Empleado> empleados) {
                for(Empleado empleado1: empleados){
                    Log.e("EMP ", empleado1.toString());
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

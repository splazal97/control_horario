package com.example.control_horario;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivityAdmin extends AppCompatActivity {
        MainViewModel mainViewModel;
    NavController navController;

    private AppBarConfiguration mAppBarConfiguration;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_admin);
            final Toolbar toolbar = findViewById(R.id.toolbar_admin);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = findViewById(R.id.drawer_layout_admin);
            final NavigationView navigationView = findViewById(R.id.nav_view_admin);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.inicioAdmin,R.id.verHorasAdmin, R.id.editarUsuarios
            )
                    .setDrawerLayout(drawer)
                    .build();

            navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
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


            mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                navController.navigate(R.id.cerrarSessionAdminFragment);
                return false;
            }
        });
        return super.onOptionsItemSelected(item);

    }
    }


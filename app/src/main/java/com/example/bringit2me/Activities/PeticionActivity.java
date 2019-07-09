package com.example.bringit2me.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bringit2me.DB.DBQueries;
import com.example.bringit2me.R;
import com.example.bringit2me.ui.login.LoginActivity;
import com.example.bringit2me.ui.login.Usuario;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class PeticionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText fecha;
    private String Or;
    private String Dest;
    private Usuario usuario;
    private LatLng locationOr;
    private LatLng locationDes;

    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peticion);
        usuario = (Usuario)getIntent().getSerializableExtra("usuario_entidad");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        fecha = findViewById(R.id.lfecha);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Places.initialize(getApplicationContext(), "AIzaSyA7MSYdDD3aQarHYYYamIaKnSiyZ4W2aoU");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteOrigen = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.frOrigen);

        // Specify the types of place data to return.
        autocompleteOrigen.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS));
        autocompleteOrigen.setHint("Origen");
        autocompleteOrigen.setCountry("CL");
        autocompleteOrigen.setTypeFilter(TypeFilter.ADDRESS);

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteOrigen.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Origen", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddressComponents().asList().get(3).getName());
                Or = place.getAddressComponents().asList().get(3).getName();
                locationOr = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Origen", "An error occurred: " + status);
            }
        });
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteDestino = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.frDest);

        // Specify the types of place data to return.
        autocompleteDestino.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS));
        autocompleteDestino.setHint("Destino");
        autocompleteDestino.setCountry("CL");
        autocompleteOrigen.setTypeFilter(TypeFilter.ADDRESS);
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteDestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Destino", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getLatLng());
                Dest = place.getAddressComponents().asList().get(3).getName();
                locationDes = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Destino", "An error occurred: " + status);
            }
        });
    }
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_busqueda) {
            Intent i = new Intent(this, BusquedaActivity.class);
            i.putExtra("usuario_entidad", usuario);
            startActivity(i);
        } else if (id == R.id.nav_PedEnvs) {

        } else if (id == R.id.nav_peticion) {
            Intent i = new Intent(this, PeticionActivity.class);
            i.putExtra("usuario_entidad", usuario);
            startActivity(i);
        } else if (id == R.id.nav_perfil) {

        } else if (id == R.id.nav_logout){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pedirfecha(View view) {
        obtenerFecha(fecha);
    }

    private void obtenerFecha(final EditText edittext){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                edittext.setText(diaFormateado + "/" + mesFormateado + "/" + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    public void solicitarPedido(View view){
        String str_fecha = fecha.getText().toString();
        String str_origen = Or;
        String str_destino = Dest;
        if(str_fecha.isEmpty() || str_origen.isEmpty() || str_destino.isEmpty()){
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_LONG).show();
        }
        else if(DBQueries.solicitarPedido(str_fecha,Or,Dest,5000,usuario.getDisplayName(), locationOr, locationDes ,this)) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario_entidad", usuario);
            startActivity(i);
            Toast.makeText(this, "Solicitud de pedido registrada con éxito", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Falló el ingreso de solicitud", Toast.LENGTH_LONG).show();
    }

}

package es.ujaen.git.sm17178_g03_practica1;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class BaseAplication extends AppCompatActivity {
    public static final String PARAM_USER = "param_user";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_aplication);
        //Si pinchamos en la imagen nos lleva a una web con un conjunto de tiendas relacionadas con la agricultura
        ImageView img = (ImageView)findViewById(R.id.ImagClick);//encuentra la imagen por su id
        img.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://jaenagricola.es"));
                startActivity(intent);
            }
        });

           //String user = getIntent().getStringExtra(PARAM_USER);


//Spinner con las diferentes variables de nuestra aplicacion
            final String[] datos = new String[]{"Riego", "PH", "Abono", "Humedad"};
            ArrayAdapter<String> adaptador = new
                    ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
            final Spinner cmbOpciones =
                    (Spinner) findViewById(R.id.CmbOpciones);
            adaptador.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            cmbOpciones.setAdapter(adaptador);
            //si se pincha en la imagen nos lleva al mapa de la zona
        ImageButton localizacion = (ImageButton) findViewById(R.id.Localizacion);
        localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.es/maps/place/Ja%C3%A9n/@37.8803112,-3.9943372,10.44z/data=!4m5!3m4!1s0xd6dd713cb5302c7:0x9cfb2c858b405702!8m2!3d37.7795941!4d-3.7849057"));
                startActivity(intent);
            }
        });
        //Si se pincha en el boton , vamos a realizar una llamada a un numero predeterminado
        Button b_telefono = (Button) findViewById(R.id.botonllamada);
        b_telefono.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Uri num = Uri.parse("tel:" + "123456789");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
            }
        });
        





    }




}

        /*LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,(LocationListener) mlocListener);*/


        /*cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id){
                        lblMensaje.setText("Seleccionado: " + datos[position]); }
                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText(""); }
                });*/




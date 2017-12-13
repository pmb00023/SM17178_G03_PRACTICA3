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
import android.widget.ImageButton;
import android.widget.Spinner;

public class BaseAplication extends AppCompatActivity {
    public static final String PARAM_USER = "param_user";

    public class MyLocationListener implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detecci—n de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            messageTextView.setText(Text);
            this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este mŽtodo se ejecuta cuando el GPS es desactivado
            messageTextView.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este mŽtodo se ejecuta cuando el GPS es activado
            messageTextView.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizaci—n (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_aplication);

           String user = getIntent().getStringExtra(PARAM_USER);



            final String[] datos = new String[]{"Elem1 su valor es " + user, "Elem2", "Elem3", "Elem4", "Elem5"};
            ArrayAdapter<String> adaptador = new
                    ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
            final Spinner cmbOpciones =
                    (Spinner) findViewById(R.id.CmbOpciones);
            adaptador.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            cmbOpciones.setAdapter(adaptador);
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
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,(LocationListener) mlocListener);


        /*cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id){
                        lblMensaje.setText("Seleccionado: " + datos[position]); }
                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText(""); }
                });*/

    }
}

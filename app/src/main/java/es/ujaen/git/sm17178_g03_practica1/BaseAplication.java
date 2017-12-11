package es.ujaen.git.sm17178_g03_practica1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BaseAplication extends AppCompatActivity {
    public static final String PARAM_USER = "param_user";

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

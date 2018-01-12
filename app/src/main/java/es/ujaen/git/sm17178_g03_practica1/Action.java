package es.ujaen.git.sm17178_g03_practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//actividad que al pulsar un boton almacena los datos en la base da datos y los muestra en la parte
//superior de la vista
public class Action extends AppCompatActivity {

    EditText editPH,editHumedad,editTemperatura,editRiego,editAbono;
    Button buttonEnviar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
//cargamos los datos de los textview para poder almacenarlos en la base de datos

        buttonEnviar=(Button)findViewById(R.id.buttonEnviar);

         final TextView txt=(TextView)findViewById(R.id.textView16);
         final String id=editTemperatura.getText().toString();


        final SQlite sQlite=new SQlite(this);
        sQlite.abrir();
        txt.setText(sQlite.leer());


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 editAbono = (EditText) findViewById(R.id.editAbono);
                 editRiego=(EditText)findViewById(R.id.editRiego) ;

                editHumedad=(EditText)findViewById(R.id.editHumedad);
                editPH=(EditText)findViewById(R.id.editPH);
                editTemperatura=(EditText)findViewById(R.id.editTemperatura);
                buttonEnviar=(Button)findViewById(R.id.buttonEnviar);
                String humedad=editHumedad.getText().toString();
                String riego =editRiego.getText().toString();
                String ph=editPH.getText().toString();
                String abono=editAbono.getText().toString();
                String temperatura=editTemperatura.getText().toString();
                final TextView txt=(TextView)findViewById(R.id.textView16);
                final String id=editTemperatura.getText().toString();
                //abrimos nuestra tabla
                sQlite.abrir();
                //pasamos los datos a la base de datos
                sQlite.anadirTab(ph,humedad,abono,temperatura,riego);
                //mostramos los datos guardados

                txt.setText(sQlite.leer());
                //cerramos la tabla de la base de datos
                sQlite.cerrar();

                Toast.makeText(getApplicationContext(),"Se han almacenado los datos",Toast.LENGTH_SHORT).show();

            }
        });



    }
}

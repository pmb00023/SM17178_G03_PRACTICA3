package es.ujaen.git.sm17178_g03_practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Action extends AppCompatActivity {

    EditText editPH,editHumedad,editTemperatura;
    Button buttonEnviar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        editHumedad=(EditText)findViewById(R.id.editHumedad);
        editPH=(EditText)findViewById(R.id.editPH);
        editTemperatura=(EditText)findViewById(R.id.editTemperatura);
        buttonEnviar=(Button)findViewById(R.id.buttonEnviar);
        final String humedad=editHumedad.getText().toString();
        final String ph=editPH.getText().toString();
         final TextView txt=(TextView)findViewById(R.id.textView16);
         final String id=editTemperatura.getText().toString();


        final SQlite sQlite=new SQlite(this);
        sQlite.abrir();
        txt.setText(sQlite.leer());


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editHumedad=(EditText)findViewById(R.id.editHumedad);
                editPH=(EditText)findViewById(R.id.editPH);
                editTemperatura=(EditText)findViewById(R.id.editTemperatura);
                buttonEnviar=(Button)findViewById(R.id.buttonEnviar);
                String humedad=editHumedad.getText().toString();
                 String ph=editPH.getText().toString();
                final TextView txt=(TextView)findViewById(R.id.textView16);
                final String id=editTemperatura.getText().toString();
                sQlite.abrir();
                sQlite.anadirTab(ph,humedad);

                txt.setText(sQlite.leer());
                sQlite.cerrar();

                Toast.makeText(getApplicationContext(),"se han almacenado los datos",Toast.LENGTH_SHORT).show();

            }
        });



    }
}

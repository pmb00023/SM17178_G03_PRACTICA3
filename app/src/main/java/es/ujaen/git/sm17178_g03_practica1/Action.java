package es.ujaen.git.sm17178_g03_practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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



        final SQlite sQlite=new SQlite(getApplicationContext());

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Humedad=editHumedad.getText().toString();
                final String PH=editPH.getText().toString();
                final String Temperatura=editTemperatura.getText().toString();
                sQlite.crearReigo(PH,Humedad,Temperatura);
                Toast.makeText(getApplicationContext(),"se han almacenado los datos",Toast.LENGTH_SHORT).show();

            }
        });



    }
}

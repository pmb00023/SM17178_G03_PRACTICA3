package es.ujaen.git.sm17178_g03_practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String datosvolatiles= "Hola";
    TextView volatil= null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            savedInstanceState.getString("volatil",datosvolatiles);

        }

        volatil = (TextView) findViewById(R.id.volatil);
        volatil.setText(datosvolatiles);
    }


    public void onIcon(View visita){
        datosvolatiles=datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);


    }
    public class TareaAutentica extends AsyncTask<ConnectionUserData,Void,String>{
        public String doInBackground(ConnectionUserData... param){
            return null;
        }
    }
}

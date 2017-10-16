package es.ujaen.git.sm17178_g03_practica1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String datosvolatiles = "Hola";
    private TextView volatil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            datosvolatiles=savedInstanceState.getString("volatil",datosvolatiles);
        }

        volatil = (TextView) findViewById(R.id.volatil);
        volatil.setText(datosvolatiles);



    }

    public void onIcon(View vista){
        datosvolatiles=datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("volatil",datosvolatiles); //No es necesario aqui pero es de ejemplo

    }


}

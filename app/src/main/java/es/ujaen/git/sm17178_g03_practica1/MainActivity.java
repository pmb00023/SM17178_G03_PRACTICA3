package es.ujaen.git.sm17178_g03_practica1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private String datosvolatiles = "Hola";
    private TextView volatil = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView)findViewById(R.id.iconoujaen);//encuentra la imagen por su id

        img.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.ujaen.es"));
                startActivity(intent);
            }
        });//funcion para llevar a la url a la variable iconoujaen en este caso una imagen


        if(savedInstanceState!=null){
            datosvolatiles=savedInstanceState.getString("volatil",datosvolatiles);
        }

        volatil = (TextView) findViewById(R.id.volatil);
        volatil.setText(datosvolatiles);//guarda datos volatiles en volatil


    }

    public void onIcon(View vista){
        datosvolatiles=datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);
    }//nos sirve para hacer una accion en volatil

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("volatil",datosvolatiles); //No es necesario aqui pero es de ejemplo  para escribir en mayuscula

    }




}

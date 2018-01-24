package es.ujaen.git.sm17178_g03_practica1;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Bitmap;

import android.os.Handler;


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

        SharedPreferences settings = this.getSharedPreferences("Mis preferencias", 0);
        //String expiresend  = settings.getString("expires", "");
         final String user = settings.getString("user","");
         final String pass = settings.getString("pass","");



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
                final String humedad=editHumedad.getText().toString();
                final String riego =editRiego.getText().toString();
                final String ph=editPH.getText().toString();
                final String abono=editAbono.getText().toString();
                final String temperatura=editTemperatura.getText().toString();
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



                new Thread(new Runnable() {
                    public void run() {
                        //Inicio del proceso de autenticacion



                        try {
                            //Iniciamos el socket
                            //Toast.makeText(getApplicationContext(),user+pass,Toast.LENGTH_SHORT).show();

                             Socket client = new Socket(InetAddress.getByName("www4.ujaen.es"), 80);

                            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            DataOutputStream output = new DataOutputStream(client.getOutputStream());
                            String temp = "GET /Agrapp.php?user=" + user + "&pass=" + pass + "&abono="+abono+ "&temperatura="+temperatura+"&ph="+ph+"&riego="+riego+"&humedad="+humedad+"\r\n\r\nHTTP/1.1\r\n";
                            output.write(temp.getBytes());
                            //Limpio la salida
                            output.flush();
                            String line=input.readLine();

                            //Recibo respuesta del servidor
                            while (line != null)
                                //Comprobamos si hemos recibido algo
                                if (line != null) {
                                    Log.d("Respuesta del servidor", line);
                                    //Comprobamos que hemos recibido bien los datos
                                    if (line.startsWith("Abono incorrecto ")) {
                                        Toast.makeText(getApplicationContext(), " Abono incorrecto, introduzca unos valores entre 0 y 100",Toast.LENGTH_LONG).show();

                                    }else if (line.startsWith("La temperatura ")){
                                        Toast.makeText(getApplicationContext(), "La temperatura supera los valores posibles",Toast.LENGTH_LONG).show();

                                    }
                                    else if (line.startsWith("Ph incorrecto ")){
                                        Toast.makeText(getApplicationContext(), "Ph incorrecto, introduzca de nuevo el abono",Toast.LENGTH_LONG).show();

                                    }
                                    else if (line.startsWith("Humedad incorrecta,")){
                                        Toast.makeText(getApplicationContext(), "Humedad incorrecta, introduzca de nuevo la humedad",Toast.LENGTH_LONG).show();

                                    }
                                    else if (line.startsWith("Valores posibles ")){
                                        Toast.makeText(getApplicationContext(), "Valores posibles de riego solo ON y OFF, introduzca alguno de estos valores",Toast.LENGTH_LONG).show();

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Ha introducido los valores correctos",Toast.LENGTH_LONG).show();


                                    }


                                }
                            //Cierro los stream de entrada y salida de datos
                            input.close();
                            output.close();
                            client.close();


                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    /*private Handler getHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            //la tarea en segundo plano ya ha terminado. Ocultamos el progreso.
            progressBar.setVisibility(View.GONE);
            //si tenemos la imagen la mostramos
            if (msg.what == 1 && msg.obj != null)
            {
                imageView.setImageBitmap((Bitmap) msg.obj);
                imageView.setVisibility(View.VISIBLE);
            }
            //si no, informamos del error
            else
            {
                Builder builder = new Builder(MainActivity.this);
                builder.setTitle(R.string.title);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage(R.string.error);
                builder.setNeutralButton(getString(R.string.ok), null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
            }


        }

    };*/



}



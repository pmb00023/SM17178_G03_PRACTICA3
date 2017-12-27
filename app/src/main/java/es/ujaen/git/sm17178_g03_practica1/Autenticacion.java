package es.ujaen.git.sm17178_g03_practica1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by pablo on 19/12/2017.
 */


    public class Autenticacion extends AsyncTask<ConnectionUserData, Void, String> {
    private String mParam1;
    private int mParam2;
    String [] SesionIDend =null;
    String [] expiresEnd = null;
    String error = "";
    private Context mContext = null;
    String [] line=null;



    public Autenticacion(Context context) {
        mContext = context;

    }



//Realizamos la conexion con el servidor para comprobar si el usuario y la contraseña son correctos
    private ConnectionUserData data;

    public String doInBackground(ConnectionUserData... param) { //Los tres puntos es de java y significa que param puede ser un array
        try {
            data = param[0];
            String s_user = data.user;
            String s_pass = data.pass;
            Socket client = new Socket(InetAddress.getByName("www4.ujaen.es"), 80);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            String temp = "GET /~jccuevas/ssmm/autentica.php?user=" + s_user + "&pass=" + s_pass + " HTTP/1.1\r\nhost:www4.ujaen.es\n\r\n\r\n";
            output.write(temp.getBytes());
            output.flush();//nos limite la salida

            String linea;
            while ((linea = input.readLine()) != null) {
                line = linea.split("&");//Se separa en dos partes la linea
                System.out.println(line);
                //Se establece la ID de la sesion y el expire gracias a la cabecera
                if(line.length==2) {
                    SesionIDend = line[0].split("SESION-ID=");

                    //la fecha quitando la cabecera
                    expiresEnd = line[1].split("EXPIRES=");
                }

            }


            if (SesionIDend != null && expiresEnd != null) {
                //Hacemos esto porque obtenemos arrays para quedarnos con los valoressid y expires
                String SesionID =SesionIDend[1];
                //Mensaje de registro .d para depuracion
                //Constante de prioridad para el método println en depuracion
                Log.d("SesionId=", SesionID);
                String Expires = expiresEnd[1];
                Log.d("Expires=", Expires);
               //Se crean las preferencias compartidas para poder guardar asi los valores
                SharedPreferences prefs =mContext.getSharedPreferences("Mis preferencias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //Se guardan en las preferencias compartidas la Sesion y el Expires

                editor.putString("SesionId", SesionID);
                editor.putString("Expires", Expires);

                //Hacemos un commit para fijar  los datos
                editor.commit();//guardado no devuelve nada

               //Se comprueba la autenticacion

                //TODO proceso de autenticación
                error="OK";
            }else {
                error="ERROR";
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO proceso de autenticación
        return error;


    }

    public void onPostExecute(String result) {
        String user=data.getUser();
        String pass=data.getPass();


// Si se devuelve OK la autenticacion es correcta
        if (result.compareToIgnoreCase("OK") == 0) {

//Guardamos el usuario y la contraseña en las clases compartidas

            SharedPreferences sesion = mContext.getSharedPreferences("Mis preferencias",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sesion.edit();
            editor.putString("user",user);
            editor.putString("pass",pass);
            editor.commit();
            // Si la autenticacion es correcta nos lanza al Base Aplication
            Intent nueva = new Intent(this.mContext, BaseAplication.class);
            nueva.putExtra(BaseAplication.PARAM_USER, data.getUser());

            mContext.startActivity(nueva);
            Toast.makeText(mContext,"Autenticación correcta", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(mContext,"Error en autenticacion",Toast.LENGTH_SHORT).show();
        }
    }
}


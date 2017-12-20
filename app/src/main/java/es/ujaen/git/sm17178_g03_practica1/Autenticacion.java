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
    String SesionIDend = "";
    String expiresEnd = "";
    Boolean error = true;
    private Context mContext = null;



    public Autenticacion(Context context) {
        mContext = context;

    }




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

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            if (line != null) {
                Log.d("Respuesta del servidor", line);
                //Comprobamos que hemos recibido bien los datos
                if (line.startsWith("SESION-ID")) {
                    String params[] = line.split("&");
                    if (params.length == 2) {
                        String sesionID[] = params[0].split("=");
                        String expires[] = params[1].split("=");
                        if (sesionID != null && expires != null) {
                            SesionIDend = sesionID[1];
                            Log.d("SesionID=", SesionIDend);
                            expiresEnd = expires[1];
                            Log.d("Expiracion=", expiresEnd);
                            error = false;





                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "ERROR";//OK si la operacion fue correcta y si no otro valor
    }


    public void onPostExecute(String result) {
        String user=data.getUser();
        String pass=data.getPass();


        if (result.compareToIgnoreCase("OK") == 0) {


            SharedPreferences sesion = mContext.getSharedPreferences("Mis preferencias",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sesion.edit();
            //editor.putString("user",user);
            //editor.putString("Pass",pass);
            editor.putString("expires", expiresEnd);
            editor.putString("sesionID", SesionIDend);
            editor.commit();
            Intent nueva = new Intent(mContext, BaseAplication.class);
            nueva.putExtra(BaseAplication.PARAM_USER, data.getUser());

            mContext.startActivity(nueva);
            //Toast.makeText(mContext,"Autentication"+user, Toast.LENGTH_SHORT).show();

        }
    }
}


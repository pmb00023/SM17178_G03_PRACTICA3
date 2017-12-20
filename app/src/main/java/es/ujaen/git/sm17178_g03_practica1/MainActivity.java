package es.ujaen.git.sm17178_g03_practica1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {







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

            Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
            int year = calendarNow.get(Calendar.YEAR);
            int monthDay =calendarNow.get(Calendar.DAY_OF_MONTH);
            int month = calendarNow.get(Calendar.MONTH)+1;
            int hour = calendarNow.get(Calendar.HOUR);
            int minute = calendarNow.get(Calendar.MINUTE);
            int second = calendarNow.get(Calendar.SECOND);
            String Date =year+"-"+month+"-"+monthDay+"-"+hour+"-"+minute+"-"+second;
            /*SharedPreferences sesion = this.getSharedPreferences("Mis preferencias",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sesion.edit();
            editor.putString("Date", Date);
            editor.commit();*/
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences settings = this.getSharedPreferences("Mis preferencias", 0);
            String expiresend  = settings.getString("expires", "");

            //if(Date.compareTo(expiresend)==0){
                /*ConnectionUserData data = new ConnectionUserData(s_user, s_pass);
                Autenticacion autenticacion = new Autenticacion(getActivity());
                autenticacion.execute(data);*/






            //}






         }






}

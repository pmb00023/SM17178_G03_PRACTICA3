package es.ujaen.git.sm17178_g03_practica1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.provider.BaseColumns._ID;


/**
 * Created by pablo on 12/01/2018.
 */
//Utilizamos SQlite para nuestra base de datos
public class SQlite extends SQLiteOpenHelper {

//Iniciamos el constructor con el nombre de la base y la version de esta

    public SQlite(Context ctx){
        super(ctx,"sQl2.db",null,1);

    }
    //Creamos nuestra tabla y le pasamos los valores que queremos que sean nuestras columnas
    public void onCreate(SQLiteDatabase db){
        String query ="CREATE TABLE campilla("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
        +"ph TEXT, humedad TEXT, abono TEXT, temperatura TEXT, riego TEXT)";
        db.execSQL(query);
    }
//metodo para actualizar si se cambia de version
    public void onUpgrade(SQLiteDatabase db,int version_ant,int version_nue){
        db.execSQL("DROP TABLE IF EXISTS campilla");
        onCreate(db);
    }

    //Metodo para añadir los valores a la tabla de la base de datos gracias a un contenvalues
    public void anadirTab(String ph1,String humedad1,String abono,String temperatura, String riego){
        ContentValues valores2= new ContentValues();
        valores2.put("ph",ph1);
        valores2.put("humedad",humedad1);
        valores2.put("abono",abono);
        valores2.put("temperatura",temperatura);
        valores2.put("riego",riego);

//getwritabledatabase sirver para poder escribir en nuestra tabla
        this.getWritableDatabase().insert("campilla",null,valores2);
//Metodo para mostrar la ultima fila almacenada en nuestra tabla
    }

    public String leer(){
        String result="";//En columnas creamos un vector para almacenar nuestros valoores
        String columnas[]={_ID,"ph","humedad","abono","temperatura","riego"};//Utilizamos la clase cursor
        //para poder cursar con facilidad nuestra tabla
        Cursor c = this.getReadableDatabase().query("campilla",columnas,null,null,null,null,null);

        int id,iu,ip,in,ic,ik;
//Obtenemos las posiciones de los valores de nuestra tabla
        id= c.getColumnIndex(_ID);
        iu= c.getColumnIndex("ph");
        ip= c.getColumnIndex("humedad");
        in= c.getColumnIndex("abono");
        ic= c.getColumnIndex("temperatura");
        ik= c.getColumnIndex("riego");
//Cogemos los de la ultima fila

        c.moveToLast();
        //Devolvemos un String el cual se mostrará en nuestra actividad
        result= "Sesión nº:"+c.getString(id)+"  Estado del riego:"+c.getString(ik)+"  Valor del PH: "+c.getString(iu)+"% Valor de la humedad: "+c.getString(ip)+"%  Abono: "+c.getString(in)+
                "%  Temperatura: "+c.getString(ic)+"Cº\n";

        return result;


    }
//Abre nuestra tabla para poder escribir en ella

    public void abrir(){
     this.getWritableDatabase();
    }
//Cierra la tabla
    public void cerrar(){
        this.close();
    }




}
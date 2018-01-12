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

public class SQlite extends SQLiteOpenHelper {



    public SQlite(Context ctx){
        super(ctx,"sQl2.db",null,1);

    }
    public void onCreate(SQLiteDatabase db){
        String query ="CREATE TABLE campilla("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
        +"ph TEXT, humedad TEXT, abono TEXT, temperatura TEXT, riego TEXT)";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db,int version_ant,int version_nue){
        db.execSQL("DROP TABLE IF EXISTS campilla");
        onCreate(db);
    }
    public void anadirTab(String ph1,String humedad1,String abono,String temperatura, String riego){
        ContentValues valores2= new ContentValues();
        valores2.put("ph",ph1);
        valores2.put("humedad",humedad1);
        valores2.put("abono",abono);
        valores2.put("temperatura",temperatura);
        valores2.put("riego",riego);


        this.getWritableDatabase().insert("campilla",null,valores2);

    }

    public String leer(){
        String result="";
        String columnas[]={_ID,"ph","humedad","abono","temperatura","riego"};
        Cursor c = this.getReadableDatabase().query("campilla",columnas,null,null,null,null,null);

        int id,iu,ip,in,ic,ik;

        id= c.getColumnIndex(_ID);
        iu= c.getColumnIndex("ph");
        ip= c.getColumnIndex("humedad");
        in= c.getColumnIndex("abono");
        ic= c.getColumnIndex("temperatura");
        ik= c.getColumnIndex("riego");


        c.moveToLast();
        result= "Sesión nº:"+c.getString(id)+"  Estado del riego:"+c.getString(ik)+"  Valor del PH: "+c.getString(iu)+"% Valor de la humedad: "+c.getString(ip)+"%  Abono: "+c.getString(in)+
                "%  Temperatura: "+c.getString(ic)+"Cº\n";

        return result;


    }


    public void abrir(){
     this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }




}
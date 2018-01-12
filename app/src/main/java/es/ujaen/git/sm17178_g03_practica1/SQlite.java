package es.ujaen.git.sm17178_g03_practica1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pablo on 12/01/2018.
 */

public class SQlite extends SQLiteOpenHelper {
    private static final String Nombre_base="sQlite.db";
    private static final int Version=1;
    private static final String TABLA_RIEGO="CREATE TABLE RIEGO(PH TEXT PRIMARY KEY,HUMEDAD TEXT,PH TEXT)";


    public SQlite(Context context) {

        super(context,Nombre_base, null, Version);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL(TABLA_RIEGO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_RIEGO);
        sqLiteDatabase.execSQL(TABLA_RIEGO);

    }
    public void crearReigo(String ph,String Humedad,String Temperatura){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){
            db.execSQL("INSERT INTO RIEGO VALUES('"+ph+"','"+Humedad+"','"+Temperatura+"')");
            db.close();
        }
    }
}
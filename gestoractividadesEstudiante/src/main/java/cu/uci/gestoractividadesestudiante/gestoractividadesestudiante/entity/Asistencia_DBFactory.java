package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tatos on 23/01/18.
 */

public class Asistencia_DBFactory extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "ASISTENCIA";

    public static final String ASISTENCIA_ID = "_id";
    public static final String ASISTENCIA_ID_ESTUDIANTE = "id_estudiante";
    public static final String ASISTENCIA_ID_ACTIVIDAD = "id_actividad";
    public static final String ASISTENCIA_PRESENTE= "presente";

    static final String DB_NAME = "GESTOR_ACTIVIDADES.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + ASISTENCIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ASISTENCIA_ID_ESTUDIANTE + " TEXT NOT NULL, " + ASISTENCIA_ID_ACTIVIDAD + " TEXT NOT NULL, " + ASISTENCIA_PRESENTE + " BOOLEAN NOT NULL);";

    public Asistencia_DBFactory(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long create(Asistencia obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put(ASISTENCIA_ID_ACTIVIDAD, obj.getId_actividad());
        row.put(ASISTENCIA_ID_ESTUDIANTE, obj.getId_estudiante());
        row.put(ASISTENCIA_PRESENTE, obj.isPresente());
        long id = db.insert(TABLE_NAME, null, row);
        db.close();
        return id;
    }

    public boolean delete(String id)
    {   SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ASISTENCIA_ID + "=" + id, null) > 0;
    }

    public boolean isPresent(String idEst,String idAct) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase == null) {
            return false;
        }
        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ASISTENCIA_ID_ACTIVIDAD+" = '"+idAct+"' AND "+ASISTENCIA_ID_ESTUDIANTE+" = '"+idEst+"' ", null);

        LinkedList<Asistencia> list = new LinkedList<>();
        while (cur.moveToNext()) {
            Asistencia obj = new Asistencia(cur.getString(0), cur.getString(1), cur.getString(2), Boolean.getBoolean(cur.getString(3)));
            list.add(obj);
        }
        cur.close();
        sqLiteDatabase.close();
        if(list.size()>0){
         return list.get(0).isPresente();
        }
        return false;
    }
}

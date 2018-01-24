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

public class Actividad_DBFactory extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "ACTIVIDAD";


    public static final String ACTIVIDAD_ID = "_id";
    public static final String ACTIVIDAD_NOMBRE = "nombre";
    public static final String ACTIVIDAD_FECHA = "fecha";


    static final String DB_NAME = "GESTOR_ACTIVIDADES.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + ACTIVIDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACTIVIDAD_NOMBRE + " TEXT NOT NULL, " + ACTIVIDAD_FECHA + " TEXT NOT NULL);";

    public Actividad_DBFactory(Context context) {
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

    public List<Actividad> getAll() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase == null) {
            return null;
        }
        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+ACTIVIDAD_FECHA, null);

        LinkedList<Actividad> list = new LinkedList<>();
        while (cur.moveToNext()) {
            Actividad obj = new Actividad(cur.getString(0), cur.getString(1), cur.getString(2));
            list.add(obj);
        }
        cur.close();
        sqLiteDatabase.close();
        return list;
    }

    public long create(Actividad obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put(ACTIVIDAD_NOMBRE, obj.getNombre());
        row.put(ACTIVIDAD_FECHA, obj.getFecha());
        long id = db.insert(TABLE_NAME, null, row);
        db.close();
        return id;
    }

    public boolean delete(String id)
    {   SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ACTIVIDAD_ID + "=" + id, null) > 0;
    }

}

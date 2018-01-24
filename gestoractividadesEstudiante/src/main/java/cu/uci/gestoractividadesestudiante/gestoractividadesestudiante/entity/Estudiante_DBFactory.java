package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tatos on 22/01/18.
 */

public class Estudiante_DBFactory extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "ESTUDIANTE";


    public static final String ESTUDIANTE_ID = "_id";
    public static final String ESTUDIANTE_USUARIO = "usuario";
    public static final String ESTUDIANTE_NOMBRE = "name";
    public static final String ESTUDIANTE_GRUPO = "grupo";

    static final String DB_NAME = "GESTOR_ACTIVIDADES.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + ESTUDIANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ESTUDIANTE_USUARIO + " TEXT NOT NULL, " + ESTUDIANTE_NOMBRE + " TEXT NOT NULL, " + ESTUDIANTE_GRUPO + " TEXT NOT NULL);";

    public Estudiante_DBFactory(Context context) {
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


    public List<Estudiante> getAll() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase == null) {
            return null;
        }
        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+ESTUDIANTE_NOMBRE, null);

        LinkedList<Estudiante> list = new LinkedList<>();
        while (cur.moveToNext()) {
            Estudiante obj = new Estudiante(cur.getString(0), cur.getString(1), cur.getString(2), cur.getString(3));
            list.add(obj);
        }
        cur.close();
        sqLiteDatabase.close();

        return list;
    }

    public List<Estudiante> getAllByGroup(String grupo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase == null) {
            return null;
        }
        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ESTUDIANTE_GRUPO+" = '"+grupo+"' ORDER BY "+ESTUDIANTE_NOMBRE, null);

        LinkedList<Estudiante> list = new LinkedList<>();
        while (cur.moveToNext()) {
            Estudiante obj = new Estudiante(cur.getString(0), cur.getString(1), cur.getString(2), cur.getString(3));
            list.add(obj);
        }
        cur.close();
        sqLiteDatabase.close();

        return list;
    }

    public long create(Estudiante obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put(ESTUDIANTE_USUARIO, obj.getUsuario());
        row.put(ESTUDIANTE_NOMBRE, obj.getNombre());
        row.put(ESTUDIANTE_GRUPO, obj.getGrupo());
        long id = db.insert(TABLE_NAME, null, row);
        db.close();
        return id;
    }

    public boolean delete(String id)
    {   SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ESTUDIANTE_ID + "=" + id, null) > 0;
    }



}

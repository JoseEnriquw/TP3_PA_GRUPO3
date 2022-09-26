package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tp3_pa_grupo3.EParqueos;
import com.example.tp3_pa_grupo3.Usuarios;

import java.util.ArrayList;
import java.util.List;

import Util.UtilsSQL;

public class SQLite_Helper_Parqueo extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BASE_DE_DATOS2.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "PARQUEO";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_MATRICULA = "Matricula";
    private static final String COLUMN_TIEMPO = "Tiempo";


    public SQLite_Helper_Parqueo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MATRICULA + " TEXT, " +
                COLUMN_TIEMPO + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(EParqueos parqueo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, parqueo.getMatricula());
        values.put(COLUMN_TIEMPO, parqueo.getTiempo());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public List<EParqueos> selectAll() {
        List<EParqueos> parqueos = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_MATRICULA, COLUMN_TIEMPO},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                EParqueos parqueo = new EParqueos();
                parqueo.setId(cursor.getInt(0));
                parqueo.setMatricula(cursor.getString(1));
                parqueo.setTiempo(cursor.getString(2));

                parqueos.add(parqueo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return parqueos;
    }

    public void delete(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);
        database.close();
    }

    public void update(EParqueos parqueo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, parqueo.getMatricula());
        values.put(COLUMN_TIEMPO, parqueo.getTiempo());
        database.update(TABLE_NAME, values, COLUMN_ID + " = " + parqueo.getId(), null);
        database.close();
    }

    public boolean  existeRegistro(String query,String[] args)
    {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().rawQuery(query, args);
        if (cursor.moveToFirst())
            return true;
        else
            return false;
    }
}

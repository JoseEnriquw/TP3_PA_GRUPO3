package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.tp3_pa_grupo3.Usuarios;
import java.util.ArrayList;
import java.util.List;

import Util.UtilsSQL;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BASE_DE_DATOS2.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Usuarios";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Nombre";
    private static final String COLUMN_EMAIL = "Correo";
    private static final String COLUMN_PASSWORD = "Contrasenia";

    public SQLite_OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(Usuarios usuarios) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, usuarios.getNombre());
        values.put(COLUMN_EMAIL, usuarios.getCorreo());
        values.put(COLUMN_PASSWORD, usuarios.getContrasenia());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public List<Usuarios> selectAll() {
        List<Usuarios> usuarios = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PASSWORD},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Usuarios usuario = new Usuarios();
                usuario.setID(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setCorreo(cursor.getString(2));
                usuario.setContrasenia(cursor.getString(3));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return usuarios;
    }

    public void delete(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);
        database.close();
    }

    public void update(Usuarios usuario) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, usuario.getNombre());
        values.put(COLUMN_EMAIL, usuario.getCorreo());
        values.put(COLUMN_PASSWORD, usuario.getContrasenia());
        database.update(TABLE_NAME, values, COLUMN_ID + " = " + usuario.getID(), null);
        database.close();
    }

    // VALIDAR LOGIN
    public boolean ValidarLogin(Usuarios usuario){
        Cursor cursor = null;
        String query = String.format(UtilsSQL.QUERY_LOGIN, usuario.getCorreo(),usuario.getContrasenia());
        cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            return true;
        else
            return false;
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

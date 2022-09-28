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

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BASE_DE_DATOS.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_USUARIO = "Usuarios";
    private static final String TABLE_NAME_PARQUEO = "PARQUEO";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ID_USUARIO = "ID_USUARIO";
    private static final String COLUMN_NAME = "Nombre";
    private static final String COLUMN_EMAIL = "Correo";
    private static final String COLUMN_PASSWORD = "Contrasenia";
    private static final String COLUMN_MATRICULA = "Matricula";
    private static final String COLUMN_TIEMPO = "Tiempo";

    public SQLite_OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_USUARIO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT" +
                ");";
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_NAME_PARQUEO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_ID_USUARIO + " INTEGER, "+
                COLUMN_MATRICULA + " TEXT, " +
                COLUMN_TIEMPO + " TEXT," +
                "FOREIGN KEY ("+COLUMN_ID_USUARIO+") REFERENCES " +TABLE_NAME_USUARIO+"("+COLUMN_ID+") )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USUARIO);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PARQUEO);
        onCreate(db);
    }

    public void insertUsuario(Usuarios usuarios) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, usuarios.getNombre());
        values.put(COLUMN_EMAIL, usuarios.getCorreo());
        values.put(COLUMN_PASSWORD, usuarios.getContrasenia());
        database.insert(TABLE_NAME_USUARIO, null, values);
        database.close();
    }

    public List<Usuarios> selectAllUsuario() {
        List<Usuarios> usuarios = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_USUARIO,
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

    public void deleteUsuario(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME_USUARIO, COLUMN_ID + " = " + id, null);
        database.close();
    }

    public void updateUsuario(Usuarios usuario) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, usuario.getNombre());
        values.put(COLUMN_EMAIL, usuario.getCorreo());
        values.put(COLUMN_PASSWORD, usuario.getContrasenia());
        database.update(TABLE_NAME_USUARIO, values, COLUMN_ID + " = " + usuario.getID(), null);
        database.close();
    }

    // VALIDAR LOGIN
    public Usuarios ValidarLogin(Usuarios usuario){
        Cursor cursor = null;
        Usuarios user=null;
        cursor = this.getReadableDatabase().rawQuery(UtilsSQL.QUERY_LOGIN, new String[]{usuario.getCorreo(),usuario.getContrasenia()});
        if (cursor.moveToFirst())
        {
            user= new Usuarios();
            user.setID(cursor.getInt(0));
            user.setNombre(cursor.getString(1));
            user.setCorreo(cursor.getString(2));
        }
        cursor.close();

        return user;
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

    public void insertParqueo(EParqueos parqueo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, parqueo.getMatricula());
        values.put(COLUMN_TIEMPO, parqueo.getTiempo());
        values.put(COLUMN_ID_USUARIO, parqueo.getUsuario().getID());
        database.insert(TABLE_NAME_PARQUEO, null, values);
        database.close();
    }

    public List<EParqueos> selectAllParqueo(String IDUsuario) {
        List<EParqueos> parqueos = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(UtilsSQL.SELECT_ALL_PARQUEO, new String[] {IDUsuario},null);
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

    public void deleteParqueo(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME_PARQUEO, COLUMN_ID + " = " + id, null);
        database.close();
    }

    public void updateParqueo(EParqueos parqueo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, parqueo.getMatricula());
        values.put(COLUMN_TIEMPO, parqueo.getTiempo());
        database.update(TABLE_NAME_PARQUEO, values, COLUMN_ID + " = " + parqueo.getId(), null);
        database.close();
    }
}

package hu.petrik.palinkaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "palinkak.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "palinka";
    private static final String COL_ID = "id";
    private static final String COL_FOZO = "fozo";
    private static final String COL_GYUMOLCS = "gyumolcs";
    private static final String COL_ALKOHOL = "alkohol";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME +" (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FOZO + " TEXT NOT NULL, " +
                COL_GYUMOLCS + " TEXT NOT NULL, " +
                COL_ALKOHOL + " INTEGER NOT NULL " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
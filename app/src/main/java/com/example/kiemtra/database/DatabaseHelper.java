package com.example.kiemtra.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "QLSP", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (" +

                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s REAL," +
                        "%s TEXT)",
                "Product", "id", "name", "price","image");
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!= newVersion)
        {
//drop
            String query = "DROP TABLE Product";
            db.execSQL(query);
//create again
            onCreate(db);
        }
    }
}

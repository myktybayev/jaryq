package kz.project.jaryq.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "jaryq.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ACCOUNTS= "accounts";

    public static final String COLUMN_PHONE_NUMBER= "phoneNumber";


    Context context;

    public StoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_ACCOUNTS + "(" +
                COLUMN_PHONE_NUMBER + " TEXT )");

        ContentValues userValue = new ContentValues();
        userValue.put(COLUMN_PHONE_NUMBER, "no");

        db.insert(TABLE_ACCOUNTS, null, userValue);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);

        onCreate(db);
    }

}
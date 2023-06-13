package sg.edu.np.mad.login;

import static android.app.SearchManager.QUERY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper {
    String title = "MyDBHandler";

    public static String DATABASE_NAME = "accountDB.db";
    public static int DATABASE_VERSION = 1;
    public static String ACCOUNTS = "accounts";
    public static String COLUMN_USERNAME = "UserName";
    public static String COLUMN_PASSWORD = "Password";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i(title, "DB Constructor");
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_COMMAND = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT)";
        Log.i(title, CREATE_TABLE_COMMAND);
        db.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        Log.i(title, "Drop & Create new DB");
        onCreate(db);
    }

    public void addUser(UserData userData) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getUsername());
        values.put(COLUMN_PASSWORD, userData.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(title, "Adding User" + values);
        db.insert(ACCOUNTS, null, values);
        db.close();
    }

    public UserData findUser(String username) {
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Log.i(title, "Find with command " + QUERY);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        UserData queryResult = new UserData();

        if (cursor.moveToFirst()) {
            queryResult.setUsername(cursor.getString(0));
            queryResult.setPassword(cursor.getString(1));
            cursor.close();
        }
        else
        {
            queryResult = null;
        }
        db.close();
        return queryResult;
    }
}
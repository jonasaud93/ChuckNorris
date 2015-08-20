package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jonas on 20/08/2015.
 */
public class JokesDb {

    private JokeDatabaseHelper dbHelper;

    public JokesDb(Context context) {
        dbHelper = new JokeDatabaseHelper(context, Constants.DB_NAME, null, 1);
    }

    public void insertJoke(String joke){
        ContentValues values = new ContentValues();
        values.put(Constants.COL_JOKE, joke);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(Constants.TABLE_NAME, null, values);
    }
}

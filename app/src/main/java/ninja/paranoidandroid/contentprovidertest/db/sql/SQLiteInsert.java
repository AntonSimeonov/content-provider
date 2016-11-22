package ninja.paranoidandroid.contentprovidertest.db.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ninja.paranoidandroid.contentprovidertest.db.DBOperations;

/**
 * Created by anton on 20.11.16.
 */

public abstract class SQLiteInsert {

    protected DBOperations mDBOperations;
    protected String sqliteOperationDescription;

    public abstract long insert();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){
        mDBOperations = new DBOperations(sqLiteDatabase);
    }
}

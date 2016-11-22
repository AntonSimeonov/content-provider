package ninja.paranoidandroid.contentprovidertest.db.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ninja.paranoidandroid.contentprovidertest.db.DBOperations;
/**
 * Created by anton on 13.05.16.
 */
public abstract class SQLiteQuery {

    protected DBOperations mDBOperations;
    protected String sqliteOperationDescription;

    public abstract Cursor query();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){

        mDBOperations = new DBOperations(sqLiteDatabase);

    }
}

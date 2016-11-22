package ninja.paranoidandroid.contentprovidertest.db.sql;

import android.database.sqlite.SQLiteDatabase;
import ninja.paranoidandroid.contentprovidertest.db.DBOperations;

/**
 * Created by anton on 20.11.16.
 */

public abstract class SQLiteDelete {

    protected DBOperations mDBOperations;
    protected String sqliteOperationDescription;

    public abstract int delete();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){

        mDBOperations = new DBOperations(sqLiteDatabase);

    }

}

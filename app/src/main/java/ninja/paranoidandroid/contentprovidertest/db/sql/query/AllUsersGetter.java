package ninja.paranoidandroid.contentprovidertest.db.sql.query;

import android.database.Cursor;

import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteQuery;

/**
 * Created by anton on 20.11.16.
 */

public class AllUsersGetter extends SQLiteQuery {


    @Override
    public Cursor query() {

        Cursor cursor = mDBOperations.getAllUsersCursor();
        return cursor;
    }

}

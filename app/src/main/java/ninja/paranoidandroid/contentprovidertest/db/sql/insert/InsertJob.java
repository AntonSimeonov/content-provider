package ninja.paranoidandroid.contentprovidertest.db.sql.insert;

import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteInsert;

/**
 * Created by anton on 21.11.16.
 */

public class InsertJob extends SQLiteInsert {

    private String mTitle;
    private String mDescription;
    private int mJobStateNumber;

    public InsertJob(String mTitle, String mDescription, int mJobStateNumber) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mJobStateNumber = mJobStateNumber;
    }

    @Override
    public long insert() {

        long newIndex = mDBOperations.insertNewJob(mTitle, mDescription, mJobStateNumber);

        return newIndex;
    }
}

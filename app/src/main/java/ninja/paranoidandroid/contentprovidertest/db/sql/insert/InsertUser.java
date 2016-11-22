package ninja.paranoidandroid.contentprovidertest.db.sql.insert;

import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteInsert;

/**
 * Created by anton on 20.11.16.
 */

public class InsertUser extends SQLiteInsert {

    private String mName;
    private String mPassword;
    private String mMall;
    private String mAddress;
    private int mJobId;

    public InsertUser(String mName, String mPassword, String mMall, String mAddress, int mJobId) {
        this.mName = mName;
        this.mPassword = mPassword;
        this.mMall = mMall;
        this.mAddress = mAddress;
        this.mJobId = mJobId;
    }

    @Override
    public long insert() {

        long newUserIndex = mDBOperations.insertNewUser(mName, mPassword, mMall, mAddress, mJobId);

        return newUserIndex;
    }
}

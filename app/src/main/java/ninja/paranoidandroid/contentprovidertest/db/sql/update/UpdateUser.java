package ninja.paranoidandroid.contentprovidertest.db.sql.update;

import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteUpdate;
import ninja.paranoidandroid.contentprovidertest.model.User;

/**
 * Created by anton on 20.11.16.
 */

public class UpdateUser extends SQLiteUpdate {

    private User mUser;
    private String mWhereClause;
    String[] mWhereArgs;

    public UpdateUser(User user, String where, String[] args){

        mUser = user;
        mWhereClause = where;
        mWhereArgs = args;

    }

    @Override
    public int update() {

        int numberUpdatedRows = mDBOperations.updateUser(mUser, mWhereClause, mWhereArgs);
        return numberUpdatedRows;
    }
}

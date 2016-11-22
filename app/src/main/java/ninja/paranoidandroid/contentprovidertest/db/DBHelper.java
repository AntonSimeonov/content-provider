package ninja.paranoidandroid.contentprovidertest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ninja.paranoidandroid.contentprovidertest.R;
import ninja.paranoidandroid.contentprovidertest.db.util.DBStatementsExtractor;




/**
 * Created by anton on 19.06.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String TAG = "DBHelper";
    private Context mContext;

    public DBHelper(Context context){
        super(context, DBContract.NAME, null, DBContract.VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String[] dbStatements = DBStatementsExtractor.getSQLiteStatemnts(mContext, R.raw.create_db);

        for (String statement: dbStatements) {

            sqLiteDatabase.execSQL(statement);
            Log.i(TAG, "Statement is " + statement);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS train_alarm");
        onCreate(sqLiteDatabase);

    }
}

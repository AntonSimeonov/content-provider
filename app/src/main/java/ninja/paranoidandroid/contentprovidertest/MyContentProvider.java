package ninja.paranoidandroid.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import ninja.paranoidandroid.contentprovidertest.db.DBContract;
import ninja.paranoidandroid.contentprovidertest.db.DBHelper;

public class MyContentProvider extends ContentProvider {

    public final static Uri CONTENT_URI = Uri.parse("content://ninja.paranoidandroid.provider");

    private final static int USERS_ALL_ROWS = 1;
    private final static int USERS_SINGLE_ROW = 2;
    private final static int JOBS_ALL_ROWS = 3;
    private final static int JOBS_SINGLE_ROW = 4;
    private final static int USER_JOB = 5;

    private final static UriMatcher uriMatcher;

    private DBHelper mDBHelper;

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("ninja.paranoidandroid.provider", "users", USERS_ALL_ROWS);
        uriMatcher.addURI("ninja.paranoidandroid.provider", "users/#", USERS_SINGLE_ROW);
        uriMatcher.addURI("ninja.paranoidandroid.provider", "jobs", JOBS_ALL_ROWS);
        uriMatcher.addURI("ninja.paranoidandroid.provider", "jobs/#", JOBS_SINGLE_ROW);
        uriMatcher.addURI("ninja.paranoidandroid.provider", "user_job/#", USER_JOB);

    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase sqLiteDatabase = null;

        try{
            sqLiteDatabase = mDBHelper.getWritableDatabase();
        }catch (SQLiteException e){
            sqLiteDatabase = mDBHelper.getReadableDatabase();
        }

        String tableName = null;
        String userId = null;

        switch (uriMatcher.match(uri)){

            case USERS_ALL_ROWS:
                tableName = DBContract.User.TABLE_NAME;
                break;
            case JOBS_ALL_ROWS:
                tableName = DBContract.Job.TABLE_NAME;
                break;
        }

        int numberDeletedRows = sqLiteDatabase.delete(tableName, selection, selectionArgs);

        return numberDeletedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data

        switch (uriMatcher.match(uri)){

            case USERS_SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.com.example.provider.users";
            case USERS_ALL_ROWS:
                return "vnd.android.cursor.dir/vnd.com.example.provider.users";
            case JOBS_SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.com.example.provider.users";
            case JOBS_ALL_ROWS:
                return "vnd.android.cursor.dir/vnd.com.example.provider.users";
            case USER_JOB:
                return "vnd.android.cursor.item/vnd.com.example.provider.user_job";
            default:
                return null;
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase sqLiteDatabase = null;

        try{
            sqLiteDatabase = mDBHelper.getWritableDatabase();
        }catch (SQLiteException e){
            sqLiteDatabase = mDBHelper.getReadableDatabase();
        }

        String tableName = null;
        String uriTable = null;

        switch (uriMatcher.match(uri)){

            case USERS_ALL_ROWS:
                tableName = DBContract.User.TABLE_NAME;
                uriTable = "ninja.paranoidandroid.provider/users/";
                break;
            case JOBS_ALL_ROWS:
                tableName = DBContract.Job.TABLE_NAME;
                uriTable = "ninja.paranoidandroid.provider/jobs/";
                break;
        }

        long numberUpdatedRows = sqLiteDatabase.insert(tableName, null, values);
        Uri newRowUri = Uri.parse(uriTable + String.valueOf(numberUpdatedRows));

        return newRowUri;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase sqLiteDatabase = null;
        try{
            sqLiteDatabase = mDBHelper.getWritableDatabase();
        }catch (SQLiteException e){
            sqLiteDatabase = mDBHelper.getReadableDatabase();
        }

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        String tableName = null;
        String userId = null;

        switch (uriMatcher.match(uri)){

            case USERS_SINGLE_ROW:
                userId = uri.getPathSegments().get(1);
                sqLiteQueryBuilder.appendWhere(DBContract.User.COLUMN_ID + " = " + userId);
                tableName = DBContract.User.TABLE_NAME;
                break;
            case USERS_ALL_ROWS:
                tableName = DBContract.User.TABLE_NAME;
                break;
            case JOBS_SINGLE_ROW:
                userId = uri.getPathSegments().get(1);
                sqLiteQueryBuilder.appendWhere(DBContract.Job.COLUMN_ID + " = " + userId);
                tableName = DBContract.Job.TABLE_NAME;
                break;
            case JOBS_ALL_ROWS:
                tableName = DBContract.Job.TABLE_NAME;
                break;
            case USER_JOB:
                userId = uri.getPathSegments().get(1);
                sqLiteQueryBuilder.appendWhere(DBContract.User.TABLE_NAME + "." + DBContract.User.COLUMN_ID + " = " + userId + " and " +
                        DBContract.User.TABLE_NAME + "." + DBContract.User.COLUMN_USER_JOB_ID + " = " +
                        DBContract.Job.TABLE_NAME + "." + DBContract.Job.COLUMN_JOB_STATE_NUMBER);
                tableName = DBContract.User.TABLE_NAME + " , " + DBContract.Job.TABLE_NAME;
                break;
        }

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, null);

       return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase sqLiteDatabase = null;

        try{
            sqLiteDatabase = mDBHelper.getWritableDatabase();
        }catch (SQLiteException e){
            sqLiteDatabase = mDBHelper.getReadableDatabase();
        }

        String tableName = null;
        String userId = null;

        switch (uriMatcher.match(uri)){

            case USERS_ALL_ROWS:
                tableName = DBContract.User.TABLE_NAME;
                break;
            case JOBS_ALL_ROWS:
                tableName = DBContract.Job.TABLE_NAME;
                break;
        }

        int numberUpdatedRows = sqLiteDatabase.update(tableName, values, selection, selectionArgs);

       return numberUpdatedRows;
    }
}

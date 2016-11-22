package ninja.paranoidandroid.contentprovidertest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ninja.paranoidandroid.contentprovidertest.db.DBContract;
import ninja.paranoidandroid.contentprovidertest.db.DBHelper;
import ninja.paranoidandroid.contentprovidertest.db.fragments.SQLiteAllQueries;
import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteInsert;
import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteQuery;
import ninja.paranoidandroid.contentprovidertest.db.sql.SQLiteUpdate;
import ninja.paranoidandroid.contentprovidertest.db.sql.insert.InsertJob;
import ninja.paranoidandroid.contentprovidertest.db.sql.insert.InsertUser;
import ninja.paranoidandroid.contentprovidertest.db.sql.query.AllJobsGetter;
import ninja.paranoidandroid.contentprovidertest.db.sql.query.AllUsersGetter;
import ninja.paranoidandroid.contentprovidertest.db.sql.query.UserJob;
import ninja.paranoidandroid.contentprovidertest.db.sql.update.UpdateUser;
import ninja.paranoidandroid.contentprovidertest.model.User;

public class MainActivity extends AppCompatActivity implements SQLiteAllQueries.CallBack{

    //SQLite fragment TAG
    public final static String SQLITE_FRAGMENT_TAG = "sqlite_fragment_tag";

    //Log
    public final static String TAG = "MainActivity";

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragmentManager();
        setSQLiteFragment();
        //createNewUser();
        //getAllUsers();
        //getUserJob();
        //getAllJobs();
        //createNewJob();
        //prepareUpdateUser();
        //new AsyncDBInit().execute();
        String[] projection = { DBContract.User.COLUMN_USER_NAME, DBContract.Job.COLUMN_JOB_TITLE};
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://ninja.paranoidandroid.provider/user_job/4"), projection, null , null, null);
        //logAllusers(cursor);
        logUserJob(cursor);
    }

    //
    private void setFragmentManager(){
        mFragmentManager = getFragmentManager();
    }

    //
    private void setSQLiteFragment(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(new SQLiteAllQueries(), SQLITE_FRAGMENT_TAG);
        ft.commit();
        //Why this worked, have to see this method later!!!
        mFragmentManager.executePendingTransactions();
    }

    private SQLiteAllQueries getSQLiteAllQueries(){

        SQLiteAllQueries sqLiteAllQueries = (SQLiteAllQueries) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);

        return sqLiteAllQueries;
    }

    private void executeQuery(SQLiteQuery sqLiteQuery){
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.query(sqLiteQuery);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }
    }

    private void insertNewUser(SQLiteInsert sqLiteInsert){
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.insert(sqLiteInsert);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }
    }

    //Remove executing sqlite fragment from activity.
    private void removeSQLiteFragment(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        SQLiteAllQueries sqLiteFragment = (SQLiteAllQueries) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);
        ft.remove(sqLiteFragment);
        ft.commit();

    }

    @Override
    public void onCursorReturned(Cursor cursor) {
        //logAllusers(cursor);
        //logAllJobs(cursor);
        logUserJob(cursor);
    }

    @Override
    public void onIsertIndexReturned(Long newInsertindex) {


    }

    @Override
    public void onNumberUpdatedReturned(Integer numberUpdated) {

    }

    @Override
    public void onNumberDeletedReturned(Integer numberDeleted) {

    }

    private void createNewUser(){

        SQLiteInsert newUser = new InsertUser("Dark Knight", "robbin", "batATtemail", "Gotam", 43);
        insertNewUser(newUser);
    }

    private void updateUser(SQLiteUpdate sqLiteUpdate){

        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.update(sqLiteUpdate);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }

    }

    private void insertNewjob(SQLiteInsert sqLiteInsert){

        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.insert(sqLiteInsert);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }

    }

    private void createNewJob(){
        SQLiteInsert sqLiteInsert = new InsertJob("Super hero", "DC univers hero", 43);
        insertNewjob(sqLiteInsert);
    }

    private void prepareUpdateUser(){

        User user  = new User("anton2", "pass", "abvmail", "my adddres", 44);
        String where = DBContract.User.COLUMN_USER_NAME + "=?";
        String[] whereArgs = {"anton"};
        SQLiteUpdate sqLiteUpdate = new UpdateUser(user, where, whereArgs);
        updateUser(sqLiteUpdate);

    }

    private void getAllUsers(){
        SQLiteQuery sqLiteQuery = new AllUsersGetter();
        executeQuery(sqLiteQuery);
    }

    private void getAllJobs(){

        SQLiteQuery sqLiteQuery = new AllJobsGetter();
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.query(sqLiteQuery);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }

    }

    private void getUserJob(){

        String[] whereArgs = {"43"};
        SQLiteQuery sqLiteQuery = new UserJob(whereArgs);
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.query(sqLiteQuery);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }

    }

    private void logAllusers(Cursor cursor){
        String name = null;
        String password = null;
        String mail = null;
        String address = null;
        int jobId = 0;

        if(cursor != null){
            if(cursor.moveToFirst()){

                do{

                    name = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_NAME));
                    password = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_PASSWORD));
                    mail = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_MAIL));
                    address = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_ADDRESS));
                    jobId = cursor.getInt(cursor.getColumnIndex(DBContract.User.COLUMN_USER_JOB_ID));
                    Log.i(TAG, "User properties are: " + name + " " + password + " " + mail + " " + address + " " + jobId);
                }while(cursor.moveToNext());

            }
        }
    }

    private void logUserJob(Cursor cursor){
        String name = null;
        String jobName = null;



        if(cursor != null){
            if(cursor.moveToFirst()){

                do{

                    name = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_NAME));
                    jobName = cursor.getString(cursor.getColumnIndex(DBContract.Job.COLUMN_JOB_TITLE));
                    Log.i(TAG, "User properties are: " + name + " " + jobName);
                }while(cursor.moveToNext());

            }
        }
    }

    private void logAllJobs(Cursor cursor){

        String title = null;
        String description = null;
        int stateNumber = 0;

        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {

                    title = cursor.getString(cursor.getColumnIndex(DBContract.Job.COLUMN_JOB_TITLE));
                    description = cursor.getString(cursor.getColumnIndex(DBContract.Job.COLUMN_JOB_DESCRIPTION));
                    stateNumber = cursor.getInt(cursor.getColumnIndex(DBContract.Job.COLUMN_JOB_STATE_NUMBER));
                    Log.i(TAG, "Job properties are: " + title + " " + description + " " + stateNumber);
                } while (cursor.moveToNext());
            }

        }

    }


    private class AsyncDBInit extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            DBHelper dbHelper = new DBHelper(MainActivity.this);
            dbHelper.getWritableDatabase();
            dbHelper.close();


            return null;
        }
    }
}

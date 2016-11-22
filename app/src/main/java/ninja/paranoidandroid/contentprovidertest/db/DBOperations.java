package ninja.paranoidandroid.contentprovidertest.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import ninja.paranoidandroid.contentprovidertest.model.User;



/**
 * Created by anton on 11.05.16.
 */
public class DBOperations {

    private final static String TAG = "DB_OPERATIONS";
    private SQLiteDatabase mSQLiteDataBase;

    public DBOperations(SQLiteDatabase sqLiteDatabase){
        mSQLiteDataBase = sqLiteDatabase;
    }


    public long insertNewUser(String userName, String userPassword, String userMail,String userAddress, int userJobId){
        ContentValues newUser = new ContentValues();
        newUser.put(DBContract.User.COLUMN_USER_NAME, userName);
        newUser.put(DBContract.User.COLUMN_USER_PASSWORD, userPassword);
        newUser.put(DBContract.User.COLUMN_USER_MAIL, userMail);
        newUser.put(DBContract.User.COLUMN_USER_ADDRESS, userAddress);
        newUser.put(DBContract.User.COLUMN_USER_JOB_ID, userJobId);

        long newUserIndex = mSQLiteDataBase.insert(DBContract.User.TABLE_NAME, null, newUser);

        return newUserIndex;
    }

    public long insertNewJob(String jobTitle, String jobDescription, int jobStateNumber){
        ContentValues newJob = new ContentValues();
        newJob.put(DBContract.Job.COLUMN_JOB_TITLE, jobTitle);
        newJob.put(DBContract.Job.COLUMN_JOB_DESCRIPTION, jobDescription);
        newJob.put(DBContract.Job.COLUMN_JOB_STATE_NUMBER, jobStateNumber);

        long newJobIndex = mSQLiteDataBase.insert(DBContract.Job.TABLE_NAME, null, newJob);

        return newJobIndex;
    }

    public ArrayList<User> getAllUsers(){

        String name = null;
        String password = null;
        String mail = null;
        String address = null;
        int jobId = 0;

        ArrayList<User> userList = new ArrayList<User>();

        Cursor cursor = mSQLiteDataBase.query(DBContract.User.TABLE_NAME, new String[]{
                DBContract.User.COLUMN_USER_NAME,
                DBContract.User.COLUMN_USER_PASSWORD,
                DBContract.User.COLUMN_USER_MAIL,
                DBContract.User.COLUMN_USER_ADDRESS,
                DBContract.User.COLUMN_USER_JOB_ID
        },null, null, null, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){

                do{

                    name = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_NAME));
                    password = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_PASSWORD));
                    mail = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_MAIL));
                    address = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_ADDRESS));
                    jobId = cursor.getInt(cursor.getColumnIndex(DBContract.User.COLUMN_USER_JOB_ID));

                    userList.add(new User(name, password, mail, address, jobId));
                }while(cursor.moveToNext());

            }
        }

        return userList;
    }

    public Cursor getAllUsersCursor(){

        Cursor cursor = mSQLiteDataBase.query(DBContract.User.TABLE_NAME, new String[]{
                DBContract.User.COLUMN_USER_NAME,
                DBContract.User.COLUMN_USER_PASSWORD,
                DBContract.User.COLUMN_USER_MAIL,
                DBContract.User.COLUMN_USER_ADDRESS,
                DBContract.User.COLUMN_USER_JOB_ID
        },null, null, null, null, null);

        return cursor;
    }

    public Cursor getAllJobs(){

        Cursor cursor = mSQLiteDataBase.query(DBContract.Job.TABLE_NAME, new String[]{
                DBContract.Job.COLUMN_JOB_TITLE,
                DBContract.Job.COLUMN_JOB_DESCRIPTION,
                DBContract.Job.COLUMN_JOB_STATE_NUMBER
        },null, null, null, null, null);

        return cursor;
    }
// Finish later.
    public int updateUser(User user, String whereClause, String[] whereArgs){
        int numberUpdatedRows = 0;

        if(user != null) {

            ContentValues updatedUser = new ContentValues();
            updatedUser.put(DBContract.User.COLUMN_USER_NAME, user.getName());
            updatedUser.put(DBContract.User.COLUMN_USER_PASSWORD, user.getPassword());
            updatedUser.put(DBContract.User.COLUMN_USER_MAIL, user.getMall());
            updatedUser.put(DBContract.User.COLUMN_USER_ADDRESS, user.getAddress());
            updatedUser.put(DBContract.User.COLUMN_USER_JOB_ID, user.getJobId());

            numberUpdatedRows = mSQLiteDataBase.update(DBContract.User.TABLE_NAME, updatedUser, whereClause, whereArgs);
        }

        return numberUpdatedRows;
    }

    public Cursor getUserJob(String[] whereArgs){


        String where = DBContract.User.TABLE_NAME + "." + DBContract.User.COLUMN_USER_JOB_ID + "=?" +
                " and " + DBContract.User.TABLE_NAME + "." + DBContract.User.COLUMN_USER_JOB_ID + " = " +
                DBContract.Job.TABLE_NAME + "." + DBContract.Job.COLUMN_JOB_STATE_NUMBER;
        Cursor cursor = null;
        if(whereArgs != null) {
            cursor = mSQLiteDataBase.query(DBContract.User.TABLE_NAME + " , " + DBContract.Job.TABLE_NAME,
                    new String[]{
                            DBContract.User.COLUMN_USER_NAME,
                            DBContract.Job.COLUMN_JOB_TITLE
                    }, where, whereArgs, null, null, null);
        }
        return cursor;

    }


//    public Cursor getUserJob(){
//
//        Cursor cursor = mSQLiteDataBase.query(DBContract.User.TABLE_NAME + " LEFT OUTER JOIN " + DBContract.Job.TABLE_NAME + " ON " +
//                        DBContract.User.TABLE_NAME+ "." +DBContract.User.COLUMN_USER_JOB_ID + " = " + DBContract.Job.TABLE_NAME + "." +DBContract.Job.COLUMN_ID,
//                new String[]{
//                        DBContract.User.COLUMN_USER_NAME,
//                        DBContract.User.COLUMN_USER_PASSWORD,
//                        DBContract.User.COLUMN_USER_MAIL,
//                        DBContract.User.COLUMN_USER_ADDRESS,
//                        DBContract.User.COLUMN_USER_JOB_ID
//                },null, null, null, null, null);
//
//        return cursor;
//
//    }


//    public long insertNewTrainAlarm(int alarmId, String trainStation, String trainNumber, String timeToNotify){
//        ContentValues values = new ContentValues();
//        values.put(DBContract.TrainAlarm.COLUMN_ALARM_ID, alarmId);
//        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_STATION, trainStation);
//        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, trainNumber);
//        values.put(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION, timeToNotify);
//
//
//        long newEntryIndex = mSQLiteDataBase.insert(DBContract.TrainAlarm.TABLE_NAME, null, values);
//        mSQLiteDataBase = null;
//        return newEntryIndex;
//    }
//
//    public void getTrainAlarm(){
//
//        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID, DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
//        cursor.moveToFirst();
//        String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
//        Log.i(TAG, "trainstation name is " + trainStationName);
//    }
//
////    public void getAllTrainAlarms(){
////
////        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID ,DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
////        if(cursor != null){
////            if(cursor.moveToFirst()){
////                do{
////
////                    String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
////                    String trainNumber = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER));
////                    String alarmTime = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION));
////                    int alarmId = cursor.getInt(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_ALARM_ID));
////                    Log.i(TAG,"Alarm id " + alarmId + "Trainstation name is " + trainStationName + "  with train number is " +trainNumber + " and alarm set at " + alarmTime);
////
////                }while (cursor.moveToNext());
////            }
////        }
////
////    }
//
//    public ArrayList<TrainAlarm> getAllTrainAlarms(){
//
//        ArrayList<TrainAlarm> trainAlarms = new ArrayList<TrainAlarm>();
//        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID ,DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
//
//        if(cursor != null){
//            if(cursor.moveToFirst()){
//                do{
//
//                    String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
//                    String trainNumber = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER));
//                    String alarmTime = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION));
//                    int alarmId = cursor.getInt(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_ALARM_ID));
//                    //Log.i(TAG,"Alarm id " + alarmId + "Trainstation name is " + trainStationName + "  with train number is " +trainNumber + " and alarm set at " + alarmTime);
//                    trainAlarms.add(new TrainAlarm(alarmId, trainStationName, trainNumber, alarmTime));
//                }while (cursor.moveToNext());
//            }
//        }
//        mSQLiteDataBase = null;
//        return trainAlarms;
//    }
//
//    public ArrayList<String[]> getDbTableDetails() {
//        Cursor c = mSQLiteDataBase.rawQuery(
//                "SELECT name FROM sqlite_master WHERE type='table'", null);
//        ArrayList<String[]> result = new ArrayList<String[]>();
//        int i = 0;
//        result.add(c.getColumnNames());
//        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
//            String[] temp = new String[c.getColumnCount()];
//            for (i = 0; i < temp.length; i++) {
//                temp[i] = c.getString(i);
//            }
//            result.add(temp);
//        }
//        mSQLiteDataBase = null;
//        return result;
//    }
//
//    public boolean deletetrainAlarm(int alarmId){
//        int deletedId = mSQLiteDataBase.delete(DBContract.TrainAlarm.TABLE_NAME, DBContract.TrainAlarm.COLUMN_ALARM_ID + "=" + alarmId, null);
//        mSQLiteDataBase = null;
//        return deletedId > 0;
//    }
//


}

package ninja.paranoidandroid.contentprovidertest.db;

/**
 * Created by anton on 19.06.16.
 */
public final class DBContract {

    public final static String NAME = "train_traveler.db";
    public final static int VERSION = 1;



    public class TrainAlarm{

        //public final static String CONTENT_URI;

        public final static String TABLE_NAME = "train_alarm";

        public final static String COLUMN_ID = "id";
        public final static String COLUMN_ALARM_ID = "alarm_id";
        public final static String COLUMN_TRAIN_STATION = "train_station";
        public final static String COLUMN_TRAIN_NUMBER = "train_number";
        public final static String COLUMN_START_NOTIFICATION = "start_notification";

    }

    public class User{

        //public final static String CONTENT_URI;

        public final static String TABLE_NAME = "user";

        public final static String COLUMN_ID = "id";
        public final static String COLUMN_USER_NAME = "user_name";
        public final static String COLUMN_USER_PASSWORD = "user_password";
        public final static String COLUMN_USER_MAIL = "user_mail";
        public final static String COLUMN_USER_ADDRESS = "user_address";
        public final static String COLUMN_USER_JOB_ID = "job_id";

    }

    public class Job{

        //public final static String CONTENT_URI;

        public final static String TABLE_NAME = "job";

        public final static String COLUMN_ID = "id";
        public final static String COLUMN_JOB_TITLE = "job_title";
        public final static String COLUMN_JOB_DESCRIPTION = "job_description";
        public final static String COLUMN_JOB_STATE_NUMBER = "job_state_number";

    }

}

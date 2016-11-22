package ninja.paranoidandroid.contentprovidertest.model;

/**
 * Created by anton on 19.11.16.
 */

public class Job {

    private String mTitle;
    private String mDescription;
    private int mJobStateNumber;

    public Job(String mTitle, String mDescription, int mJobStateNumber) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mJobStateNumber = mJobStateNumber;
    }

    public Job() {
    }

    public String getTitle() {
        return mTitle;
    }

    public String getmescription() {
        return mDescription;
    }

    public int getJobStateNumber() {
        return mJobStateNumber;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setJobStateNumber(int mJobStateNumber) {
        this.mJobStateNumber = mJobStateNumber;
    }
}

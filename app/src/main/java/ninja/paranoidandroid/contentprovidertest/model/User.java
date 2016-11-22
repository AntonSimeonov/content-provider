package ninja.paranoidandroid.contentprovidertest.model;

/**
 * Created by anton on 19.11.16.
 */

public class User {

    private String mName;
    private String mPassword;
    private String mMall;
    private String mAddress;
    private int mJobId;

    public User(String mName, String mPassword, String mMall, String mAddress, int mJobId) {
        this.mName = mName;
        this.mPassword = mPassword;
        this.mMall = mMall;
        this.mAddress = mAddress;
        this.mJobId = mJobId;
    }

    public User() {
    }

    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getMall() {
        return mMall;
    }

    public String getAddress() {
        return mAddress;
    }

    public int getJobId() {
        return mJobId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public void setMall(String mMall) {
        this.mMall = mMall;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setJobId(int mJobId) {
        this.mJobId = mJobId;
    }
}

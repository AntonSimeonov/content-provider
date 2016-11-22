package ninja.paranoidandroid.contentprovidertest.db.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by anton on 11.05.16.
 */
public class DBStatementsExtractor {

    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                //text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    public static String[] getSQLiteStatemnts(Context ctx, int resId){

        String rawTextfileString;
        rawTextfileString = readRawTextFile(ctx, resId);

        return rawTextfileString.split(";");

    }

}

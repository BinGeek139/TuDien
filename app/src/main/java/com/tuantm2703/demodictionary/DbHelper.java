package com.tuantm2703.demodictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.tuantm2703.demodictionary.Model.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="DbTestdictionary2.db";
    private Context mContext=null;
    private static final String DB_PATH_SUFFIX = "/databases/";


    public DbHelper(Context context) {
        super(context,DB_NAME,null,1);
        mContext=context;
        File file = context.getDatabasePath(DB_NAME);//
        if(!file.exists()){
            try{
                copyDatabaseFromAssets();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void copyDatabaseFromAssets() {
        try{
            InputStream iP = mContext.getAssets().open(DB_NAME);
            String outputStream = getPathDBSystem();
            File file = new File(mContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!file.exists()){
                file.mkdir();
            }
            OutputStream dBoutputStream = new FileOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = iP.read(buffer))> 0){
                dBoutputStream.write(buffer, 0, lenght);
            }
            dBoutputStream.flush();
            dBoutputStream.close();
            iP.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getPathDBSystem() {
        return mContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

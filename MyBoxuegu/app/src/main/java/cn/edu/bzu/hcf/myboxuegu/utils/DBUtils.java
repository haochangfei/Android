package cn.edu.bzu.hcf.myboxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.bzu.hcf.myboxuegu.bean.UserBean;
import cn.edu.bzu.hcf.myboxuegu.sqlite.SQLiteHelper;

/**
 * Created by Administrator on 2018/5/16.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils (Context context){
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void saveUserInfo(UserBean bean){
        ContentValues cv = new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
    }

}

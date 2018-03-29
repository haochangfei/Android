package boxuegu.com.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 神思 on 2018/3/29.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName","");
        return userName;
    }
}

package cn.edu.bzu.hcf.myboxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.bzu.hcf.myboxuegu.bean.ExercisesBean;

/**
 * Created by 神思 on 2018/3/29.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName","");
        return userName;
    }
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if("infos".equals(parser.getName())){
                        exercisesInfos = new ArrayList<ExercisesBean>();
                    } else if("exercises".equals(parser.getName())){
                        exercisesInfo = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.subjectId = Integer.parseInt(ids);
                    } else if("subject".equals(parser.getName())){
                        String subject = parser.nextText();
                        exercisesInfo.subject = subject;
                    } else if("a".equals(parser.getName())){
                        String a = parser.nextText();
                        exercisesInfo.a = a;
                    } else if("b".equals(parser.getName())){
                        String b = parser.nextText();
                        exercisesInfo.b = b;
                    } else if("c".equals(parser.getName())){
                        String c = parser.nextText();
                        exercisesInfo.c = c;
                    } else if("d".equals(parser.getName())){
                        String d = parser.nextText();
                        exercisesInfo.d = d;
                    } else if("answer".equals(parser.getName())){
                        String answer = parser.nextText();
                        exercisesInfo.answer = Integer.parseInt(answer);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("exercises".equals(parser.getName())){
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;
    }
}

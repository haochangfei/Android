package cn.edu.bzu.hcf.myboxuegu.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.bzu.hcf.myboxuegu.R;
import cn.edu.bzu.hcf.myboxuegu.bean.UserBean;
import cn.edu.bzu.hcf.myboxuegu.utils.AnalysisUtils;
import cn.edu.bzu.hcf.myboxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName,tv_signature,tv_user_Name,tv_sex;
    private RelativeLayout rl_nivkName,rl_sex,rl_signature,rl_title_bar;
    private String spUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        //setListenter();
    }
    private void init(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4Fe"));
        rl_nivkName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_user_Name = (TextView) findViewById(R.id.tv_user_name);
        tv_sex= (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
    }
    private void initData(){
        UserBean bean = new UserBean();
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        if(bean == null){
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
        }
    }
    @Override
    public void onClick(View v) {

    }
}

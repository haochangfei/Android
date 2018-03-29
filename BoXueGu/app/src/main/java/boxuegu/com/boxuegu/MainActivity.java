package boxuegu.com.boxuegu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.com.boxuegu.view.MyInfoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout mBodyLayout;
    private LinearLayout mBottomLayout;
    private View mCoursBtn,mExercisesBtn,mMyInfoBtn;
    private TextView tv_course,tv_exercises,tv_myInfo,tv_back,tv_main_title;
    private ImageView iv_course,iv_exercises,iv_myInfo;
    private RelativeLayout r1_title_bar;
    private MyInfoView mMyInfoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initBottomBar();
        setListener();
        serInitStatus();
    }
    private void init(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        r1_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        r1_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }

    private void initBottomBar(){
        mBottomLayout = (LinearLayout)findViewById(R.id.main_bottom_bar);

        mCoursBtn = findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);

        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);

        iv_course = (ImageView)findViewById(R.id.bottom_bar_image_course);
        iv_exercises = (ImageView)findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo = (ImageView)findViewById(R.id.bottom_bar_image_myinfo);
    }

    private void initBodyLayout(){
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            default:break;
        }
    }
    private void setListener(){
        for(int i = 0; i < mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    private void clearBottomImageState(){
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for(int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }
    private void setSelectedStatus(int index){
        switch (index){
            case 0:
                mCoursBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                r1_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                r1_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                r1_title_bar.setVisibility(View.GONE);
                break;
        }
    }
    private void removeALLView(){
        for(int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    private void serInitStatus(){
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }
    private void selectDisplayView(int index){
        removeALLView();
        createView(index);
        setSelectedStatus(index);
    }
    private void createView(int viewIndex){
        switch (viewIndex){
            case 0:
                break;
            case 1:
                break;
            case 2:
                if(mMyInfoView == null){
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                }else{
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
                break;
        }
    }
    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                MainActivity.this.finish();
                if(readLoginStatus()){
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private boolean readLoginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin",false);
        return isLogin;
    }
    private void clearLoginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            if(isLogin){
                clearBottomImageState();
                selectDisplayView(0);
            }
            if(mMyInfoView != null){
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }
}

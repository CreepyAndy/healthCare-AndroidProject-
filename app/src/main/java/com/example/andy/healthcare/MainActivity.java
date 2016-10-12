package com.example.andy.healthcare;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.healthcare.view.AdminActivity;
import com.xia.services.Query;
import com.xia.services.Verify;

import org.ksoap2.serialization.SoapObject;

public class MainActivity extends Activity implements OnClickListener{
    private Button refreshBasicIndex;
    private Button updateBasicIndex;
    private double hStatus;
    private ViewPager vp;
    private PagerAdapter mAdapter;//适配器
    private List<View> mViews = new ArrayList<View>();//数据集
    private LinearLayout mlay1;
    private LinearLayout mlay2;
    private LinearLayout mlay3;
    private LinearLayout mlay4;

    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();//用来 初始化点击事件
        TextView userText = (TextView)findViewById(R.id.userText);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        userText.setText("欢迎用户"+" "+id);
//        refreshBasicIndex=(Button)findViewById(R.id.refreshBasicIndex);
//        refreshBasicIndex.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                QueryTask queryTask = new QuerzyTask();
//                //启动后台任务
//                queryTask.execute(id);
//                //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    class UpdateTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            Query.updateBasicIndexById(params[0],params[1],params[2],params[3],params[4]);
            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        }
    }

    class QueryTask extends AsyncTask<String, Integer, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            SoapObject detail = null;
            List<String> result = null;
            try {
                detail = Query.queryBasicIndexById(params[0]);
                result = Query.parseBasicInfo(detail);
            } catch (Exception e) {

            }
            //将结果返回给onPostExecute方法
            return result;
        }

        @Override
        //此方法可以在主线程改变UI
        protected void onPostExecute(List<String> result) {
            // 将WebService返回的结果显示在TextView中

            EditText h = (EditText)findViewById(R.id.b_heartRate);
            h.setText(result.get(1));
            EditText height = (EditText)findViewById(R.id.b_height);
            height.setText(result.get(2));
            EditText o = (EditText)findViewById(R.id.b_oxygen);
            o.setText(result.get(4));
            EditText w = (EditText)findViewById(R.id.b_weight);
            w.setText(result.get(5));
            hStatus=Double.parseDouble(result.get(1))+Double.parseDouble(result.get(2))+Double.parseDouble(result.get(4))+Double.parseDouble(result.get(5));
            hStatus=hStatus/(100+180+99+70);

        }
    }



    //初始化点击事件
    private void initEvent() {


        mlay1.setOnClickListener(this);
        mlay2.setOnClickListener(this);
        mlay3.setOnClickListener(this);
        mlay4.setOnClickListener(this);

        //设置滑动ViewPager时的事件

        vp.setOnPageChangeListener(new OnPageChangeListener() {


            //主要在这个方法里操作，当选中相应的view时一系列的响应事件
            public void onPageSelected(int arg0) {

                resetImg();
                int item = vp.getCurrentItem();
                switch(item){

                    case 0:
                        img1.setImageResource(R.drawable.health_examination);
                        break;
                    case 1:
                        img2.setImageResource(R.drawable.data);
                        break;
                    case 2:
                        img3.setImageResource(R.drawable.health_resolution);
                        break;
                    case 3:
                        img4.setImageResource(R.drawable.mine);
                        break;
                }
            }
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }



    //该方法初始化各个view
    private void initView() {

        vp = (ViewPager) findViewById(R.id.vp);

        //获取底部的各个线性布局
        mlay1 = (LinearLayout) findViewById(R.id.lay_hudie);
        mlay2 = (LinearLayout) findViewById(R.id.lay_set);
        mlay3 = (LinearLayout) findViewById(R.id.lay_user);
        mlay4 = (LinearLayout) findViewById(R.id.lay_yang);

        //获取各个imageView
        img1 = (ImageButton) findViewById(R.id.ibtn_hudie);
        img2 = (ImageButton) findViewById(R.id.ibtn_set);
        img3 = (ImageButton) findViewById(R.id.ibtn_user);
        img4 = (ImageButton) findViewById(R.id.ibtn_yang);

        //下面将view加入到数据集中
        View v1 = LayoutInflater.from(this).inflate(R.layout.tab_exam, null);
        View v2 = LayoutInflater.from(this).inflate(R.layout.tab_data, null);
        View v3 = LayoutInflater.from(this).inflate(R.layout.tab_solution, null);
        View v4 = LayoutInflater.from(this).inflate(R.layout.tab_mine, null);

        mViews.add(v1);
        mViews.add(v2);
        mViews.add(v3);
        mViews.add(v4);

        //然后再根据数据集配置适配器

        mAdapter = new PagerAdapter() {


            //销毁item
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                container.removeView(mViews.get(position));
            }


            //初始化item
            public Object instantiateItem(ViewGroup container, int position) {

                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            //TODO
            //这个方法是什么意思，有待进一步查解
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }
            //获得适配的总数目
            public int getCount() {
                return mViews.size();
            }
        };
        //为ViewPager设置适配器
        vp.setAdapter(mAdapter);
    }

    //点击事件，会把执行点击的控件传进来，即view
    public void onClick(View v) {
        resetImg();
        switch(v.getId()){
            case R.id.lay_hudie:
                vp.setCurrentItem(0);//设置ViewPager当前的view
                img1.setImageResource(R.drawable.health_examination);
                refreshBasicIndex=(Button)findViewById(R.id.refreshBasicIndex);
                updateBasicIndex=(Button)findViewById(R.id.updateBasics);
                updateBasicIndex.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        UpdateTask updateTask = new UpdateTask();
                        //启动后台任务
                        EditText h = (EditText)findViewById(R.id.b_heartRate);
                        EditText height = (EditText)findViewById(R.id.b_height);
                        EditText o = (EditText)findViewById(R.id.b_oxygen);
                        EditText w = (EditText)findViewById(R.id.b_weight);
                        updateTask.execute(id,h.getText().toString(),height.getText().toString(),o.getText().toString(),w.getText().toString());
                        //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
                    }
                });
                refreshBasicIndex.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        QueryTask queryTask = new QueryTask();
                        //启动后台任务
                        queryTask.execute(id);
                        //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.lay_set:
                vp.setCurrentItem(1);//设置ViewPager当前的view
                img2.setImageResource(R.drawable.data);
                break;
            case R.id.lay_user:
                vp.setCurrentItem(2);//设置ViewPager当前的view
                img3.setImageResource(R.drawable.health_resolution);
                TextView h = (TextView)findViewById(R.id.healthStatus);
                h.setText(hStatus*100+"");
                break;
            case R.id.lay_yang:
                vp.setCurrentItem(3);//设置ViewPager当前的view
                img4.setImageResource(R.drawable.mine);
                break;
        }
    }
    //该方法用来将图片还原到初始状态
    private  void resetImg(){
        img1.setImageResource(R.drawable.health_examination);
        img2.setImageResource(R.drawable.data);
        img3.setImageResource(R.drawable.health_resolution);
        img4.setImageResource(R.drawable.mine);
    }
}
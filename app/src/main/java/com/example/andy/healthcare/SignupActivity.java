package com.example.andy.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.xia.services.Verify;

import org.w3c.dom.Text;

/**
 * Created by Andy on 2016/10/9.
 */
public class SignupActivity extends AppCompatActivity {
    private TextView id;
    private TextView name;
    private TextView psw;
    private DatePicker date;
    private Button submit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        id=(TextView)findViewById(R.id.s_id);
        name=(TextView)findViewById(R.id.s_name);
        psw=(TextView)findViewById(R.id.s_psw);
        date=(DatePicker)findViewById(R.id.s_datepicker);
        submit=(Button)findViewById(R.id.submit_signup);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                SubmitTask submitTask = new SubmitTask();
                //启动后台任务

                submitTask.execute(id.getText().toString(),name.getText().toString(),psw.getText().toString(),date.getYear()+"",date.getMonth()+"",date.getDayOfMonth()+"");

                //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
            }
        });
    }
    class SubmitTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            Verify.createAccount(params[0],params[1],params[2],params[3],params[4],params[5]);
            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(SignupActivity.this,"注册成功请登录",Toast.LENGTH_LONG).show();
        }
    }
}

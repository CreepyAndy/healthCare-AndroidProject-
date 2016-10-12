package com.example.andy.healthcare;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.healthcare.view.AdminActivity;
import com.xia.services.Verify;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
public class LoginActivity extends AppCompatActivity {
    private EditText loginId;
    private EditText password;
    private Button signin;
    private TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginId=(EditText)findViewById(R.id.txt_email);
        password=(EditText)findViewById(R.id.txt_password);
        create=(TextView)findViewById(R.id.txt_create);
        signin=(Button)findViewById(R.id.email_sign_in_button);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                QueryAddressTask queryAddressTask = new QueryAddressTask();
                //启动后台任务
                queryAddressTask.execute(loginId.getText().toString(),password.getText().toString());
                //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
            }
        });
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);//
                //Toast.makeText(LoginActivity.this,verify.verifyUser(loginId.getText().toString(),password.getText().toString()),Toast.LENGTH_SHORT).show();
            }
        });
    }


    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            // 查询手机号码（段）信息*/
            String result="ok";
            try {
                result = Verify.verifyUser(params[0],params[1]);
                return result+params[0];
            } catch (Exception e) {

               // Log.e("loginFail",e.toString());
            }
            //将结果返回给onPostExecute方法
            return result+params[0];
        }

        @Override
        //此方法可以在主线程改变UI
        protected void onPostExecute(String result) {
            // 将WebService返回的结果显示在TextView中
            if(result.contains("false"))
                Toast.makeText(LoginActivity.this,"用户名密码错误",Toast.LENGTH_SHORT).show();
            if(result.contains("true")){
                if(Integer.parseInt(result.substring(4))==100){
                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                    intent.putExtra("id","管理员");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", result.substring(4));
                    startActivity(intent);
                }
            }
        }
    }

}

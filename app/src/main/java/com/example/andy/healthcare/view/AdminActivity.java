package com.example.andy.healthcare.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.healthcare.R;
import com.xia.services.po.Employee;
import com.xia.services.Query;

import java.util.List;

/**
 * Created by Andy on 2016/10/8.
 */
public class AdminActivity extends AppCompatActivity {
    private List<Employee> employees = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        QueryUserTask queryUserTask = new QueryUserTask();
        queryUserTask.execute();
        //ListView listView = (ListView)findViewById(R.id.listView);
    }

    class QueryUserTask extends AsyncTask<Void,Integer,List<Employee>>{
        @Override
        protected List<Employee> doInBackground(Void... params) {
            employees=Query.queryAllPersonalInfo();
            return employees;
        }

        @Override
        protected void onPostExecute(List<Employee> employees) {
            super.onPostExecute(employees);
//            MyAdapter myAdapter = new MyAdapter(AdminActivity.this);
//            ListView listView = (ListView)findViewById(R.id.listView);
//            listView.setAdapter(myAdapter);
            MyAdapter myAdapter = new MyAdapter(AdminActivity.this,employees,R.layout.item);
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(myAdapter);
        }
    }

    class MyAdapter extends BaseAdapter{
        private List<Employee> employees;//数据
        private int resource;
        private Context context;
        private LayoutInflater inflator;
        private TextView id;
        private TextView name;
        private TextView psw;
        MyAdapter(Context context,List<Employee>employees,int resource){
            this.context = context;
            this.employees = employees;
            this.resource = resource;
        }
        @Override
        public int getCount() {
            return employees.size();
        }
        @Override
        public Object getItem(int position) {
            return employees.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflator.inflate(resource, null);
                id = (TextView)convertView.findViewById(R.id.i_id);   //为了减少开销，则只在第一页时调用findViewById
                name =(TextView) convertView.findViewById(R.id.i_name);
                psw = (TextView)convertView.findViewById(R.id.i_psw);
            }
            Employee person = employees.get(position);
            id.setText(person.getId()+"");
            name.setText(person.getName()+"");
            psw.setText(person.getPassword()+"");
            return convertView;
        }
    }
}

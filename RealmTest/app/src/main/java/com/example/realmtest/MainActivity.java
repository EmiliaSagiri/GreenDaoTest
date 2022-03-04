package com.example.realmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private final String TAG = "666";
 List<User> userSumList =new ArrayList<>();

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    private MyAdapter adapter;
    private DBManager dbManager;
    private  List<User> userList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = DBManager.getInstance(this);
        userList = dbManager.queryUserList();
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MyAdapter(this,userSumList);
        recyclerView.setAdapter(adapter);
        /*
        接口实现点击事件
         */
        adapter.setOnClickMyTextView(new MyAdapter.onClickMyTextView() {
            @Override
            public void mytextviewOnclick(View view, int id) {
                Toast.makeText(MainActivity.this,
                        " 编号为: "+userList.get(id).getNumber()+"\n"+" 名字为："+userList.get(id).getName()
                                +"\n"+" 性别为： "+userList.get(id).getSex()+"\n"+" 分数为： "+userList.get(id).getGrade()
                        ,Toast.LENGTH_SHORT).show();
            }
        });
        initView();
        findData();


    }

    private void initView() {
        // 根据setContentView(R.layout.activity_main)方法指定的布局中的id初始化对象
        // 8个按钮
        btn1 = findViewById(R.id.add);
        btn2 = findViewById(R.id.delete);
        btn3 = findViewById(R.id.update);
        btn4 = findViewById(R.id.find);

        editText1 = findViewById(R.id.number_text);
        editText2 = findViewById(R.id.studentName_text);
        editText3 = findViewById(R.id.sex_text);
        editText4 = findViewById(R.id.grade_text);


        // 5个输入框（4个输入框需要在下面的onClick()方法中用去获取输入的文本，所以在全局进行声明）
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    public void findData(){
        userList = dbManager.queryUserList();
        userSumList.clear();
          for (User x :userList){
              userSumList.add(x);
          }
          adapter.setData(userSumList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //插入数据按钮
            case R.id.add:
                User user =new User();
                user.setNumber(Long.parseLong(String.valueOf(editText1.getText())));
                user.setName(String.valueOf(editText2.getText()));
                user.setSex(String.valueOf(editText3.getText()));
                user.setGrade(Integer.parseInt(String.valueOf(editText4.getText())));
                dbManager.insertUser(user);
                findData();

                break;
            //删除数据按钮
            case R.id.delete:
                for(User x :userList){
                   if(x.getNumber()==Long.parseLong(String.valueOf(editText1.getText()))){
                       dbManager.deleteUser(x);
                   }

                }
                findData();

                break;
            //删除数据按钮后面的清除按钮

            //更新数据按钮
            case R.id.update:

                for(User user1 :userList){
                    if(user1.getNumber()==Long.parseLong(String.valueOf(editText1.getText()))){
                        user1.setName(String.valueOf(editText2.getText()));
                        user1.setSex(String.valueOf(editText3.getText()));
                        user1.setGrade(Integer.parseInt(String.valueOf(editText4.getText())));
                        dbManager.updateUser(user1);
                    }

                }
                findData();
                break;

            //查询全部按钮
            case R.id.find:
                for (User data:userList){
                    Log.i(TAG, "编号为: "+data.getNumber()+" 名字为："+data.getName()
                            +" 性别为： "+data.getSex()+" 分数为： "+data.getGrade());
                }
                break;

            default:
                break;
        }
    }
}
package com.example.schedule2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();
        adapter = new UserListAdapter(getApplicationContext(), userList, this);
        listView.setAdapter(adapter);


        try {
            //JSON오브젝트에서 웹데이터를 가져옴
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            //배열형태로 받아옴
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            //사용자 개수 관리
            int count = 0;
            String userID, userPassword, userGender, userMajor;
            //개수를 늘려가며 배열의 모든것을 탐색
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userGender = object.getString("userGender");
                userMajor = object.getString("userMajor");
                User user = new User(userID, userPassword, userGender, userMajor);
                //현재 아이디가 admin을 제외
                if (!userID.equals("admin"))
                    userList.add(user);
                count++;
            }

        }
        //오류가 발생했을 경우 출력
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

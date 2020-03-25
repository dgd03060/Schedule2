package com.example.schedule2;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class UserListAdapter extends BaseAdapter{
    private Context context;
    private List<User> userList;
    private Activity parentActivity;

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity) {
        this.context = context;
        this.userList = userList;
        this.parentActivity = parentActivity;
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.user, null);
        final TextView userID = (TextView) v.findViewById(R.id.userID);
        TextView userPassword = (TextView) v.findViewById(R.id.userPassword);
        TextView userGender = (TextView) v.findViewById(R.id.userGender);
        TextView userMajor = (TextView) v.findViewById(R.id.userMajor);

        userID.setText(userList.get(position).getUserID());
        userPassword.setText(userList.get(position).getUserPassword());
        userGender.setText(userList.get(position).getUserGender());
        userMajor.setText(userList.get(position).getUserMajor());

        v.setTag(userList.get(position).getUserID());

        //회원삭제 버튼 클릭시
        //객체선언
        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);;
        deleteButton.setOnClickListener(new View.OnClickListener(){ //이벤트 실행
            @Override
            public void onClick(View view)
            {//결과값 받아줄 리스너 생성
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{//response값을 받을수 있도록 함
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Toast.makeText(context.getApplicationContext(), " 회원 삭제에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            if(success){
                                userList.remove(position);
                                notifyDataSetChanged();
                            }
                        }//오류발생시 출력
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };//딜레트 리퀘스트 출력
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });

        return v;
    }
}
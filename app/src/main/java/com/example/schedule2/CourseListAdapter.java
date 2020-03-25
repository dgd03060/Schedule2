package com.example.schedule2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class CourseListAdapter extends BaseAdapter{
    private Context context;
    private List<Course> courseList;
    private Fragment parent;

    public CourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);//해당 위치의 값을 리스트뷰에 뿌려줌
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View View, final ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        TextView courseGrade = (TextView) v.findViewById(R.id.courseGrade);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        TextView courseDivide = (TextView) v.findViewById(R.id.courseDivide);
        TextView coursePersonnel = (TextView) v.findViewById(R.id.coursePersonnel);
        TextView courseProfessor = (TextView)v.findViewById(R.id.courseProfessor);
        TextView courseTime = (TextView) v.findViewById(R.id.courseTime);

        courseGrade.setText("날짜 : "+courseList.get(position).getCourseGrade());
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText("시간 : "+courseList.get(position).getCourseCredit());
        courseDivide.setText("날짜 : "+courseList.get(position).getCourseDivide());
        coursePersonnel.setText("시간 : "+courseList.get(position).getCoursePersonnel());
        courseProfessor.setText(courseList.get(position).getCourseProfessor());


        v.setTag(courseList.get(position).getCourseID());

        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){//등록에승공
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("내일정에 추가되었습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();


                            }else{//실패한경우
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("내일정 추가에 실패하였습니다")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };//Response.Listener 완료

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                AddRequest addRequest = new AddRequest(userID, courseList.get(position).getCourseID() + "",responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                queue.add(addRequest);
            }
        });
        return v;
    }
}

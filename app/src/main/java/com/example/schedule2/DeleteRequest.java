package com.example.schedule2;
;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {

    final static private String URL = "http://rbgk60.cafe24.com/Delete.php";
    private Map<String, String> parameters;

    public  DeleteRequest(String userID, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);//특정한 요청을 호스트 방식으로 전송
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }
    @Override
    public  Map<String, String> getParams(){
        return parameters;
    }

}

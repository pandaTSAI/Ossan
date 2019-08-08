package com.example.ossan;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ossan.bean.OssanBean;
import com.example.ossan.common.Common;
import com.example.ossan.task.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.sql.Timestamp;

public class JoinFragment extends Fragment {
    private Activity activity;
    private BottomNavigationView bottomNavigationView;
    private EditText fragment_join_ediEmail,fragment_join_ediPassword,fragment_join_ediName,fragment_join_ediPhone,fragment_join_ediAddress,fragment_join_ediNickname;
    private Button fragment_join_btnsubmit;
    private Task joinTask;
    private String errorEmail = null,errorPassword = null,errorName = null,errorPhone = null,errorAddress = null,errorNickName = null;
    private TextView tvErrorEmail,tvErrorPassword,tvErrorName,tvErrorPhone,tvErrorAddress,tvErrorNickName;
    Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity.setTitle(R.string.join_name);
        return inflater.inflate(R.layout.fragment_join, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        //找到layout元件
        fragment_join_ediEmail = view.findViewById(R.id.fragment_join_ediEmail);
        fragment_join_ediPassword = view.findViewById(R.id.fragment_join_ediPassword);
        fragment_join_ediName = view.findViewById(R.id.fragment_join_ediName);
        fragment_join_ediPhone = view.findViewById(R.id.fragment_join_ediPhone);
        fragment_join_ediAddress = view.findViewById(R.id.fragment_join_ediAddress);
        fragment_join_ediNickname = view.findViewById(R.id.fragment_join_ediNickname);
        tvErrorEmail = view.findViewById(R.id.fragment_join_tv_errorEmail);
        tvErrorPassword = view.findViewById(R.id.fragment_join_tv_errorPassword);
        tvErrorName = view.findViewById(R.id.fragment_join_tv_errorName);
        tvErrorPhone = view.findViewById(R.id.fragment_join_tv_errorPhone);
        tvErrorAddress = view.findViewById(R.id.fragment_join_tv_errorAddress);
        tvErrorNickName = view.findViewById(R.id.fragment_join_tv_errorNickName);

        fragment_join_btnsubmit = view.findViewById(R.id.fragment_join_btnsubmit);
        fragment_join_btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //抓取使用者輸入的值
                String email = fragment_join_ediEmail.getText().toString().trim();
                String password = fragment_join_ediPassword.getText().toString().trim();
                String name = fragment_join_ediName.getText().toString().trim();
                String phone = fragment_join_ediPhone.getText().toString().trim();
                String address = fragment_join_ediAddress.getText().toString().trim();
                String nickname = fragment_join_ediNickname.getText().toString().trim();

                //檢查可不可以連線
                if (Common.networkConnected(getActivity())){
                    //設定MeoberBean屬性
                    OssanBean ob = new OssanBean();
                    ob.setEmail(email);
                    ob.setPassword(password);
                    ob.setName(name);
                    ob.setPhone(phone);
                    ob.setAddress(address);
                    ob.setNickname(nickname);

                    ob.setRegisterTime(ts);
                    ob.setFBLogin(false);
                    ob.setGoogleLogin(false);
//                    ob.setTwMiddle(false);
//                    ob.setTwNorth(false);
//                    ob.setTwOther(false);
//                    ob.setTwSouth(false);

                    try {
                        //設定連線的url 對應servlet 的 @WebServlet("/registerAndroid")
                        String url = Common.URL + "/registerAndroid";
                        Gson gsonsend = new Gson();

//                        JSONObject jsonObject1 =new JSONObject();
//                        String name = jsonObject1.getString("name");

                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("action","register");
                        jsonObject.addProperty("email",ob.getEmail());
                        jsonObject.addProperty("password",ob.getPassword());
                        jsonObject.addProperty("name",ob.getName());
                        jsonObject.addProperty("phone",ob.getPhone());
                        jsonObject.addProperty("address",ob.getAddress());
                        jsonObject.addProperty("nickname",ob.getNickname());
                        String jsonOut = jsonObject.toString();
                        joinTask = new Task(url, jsonOut);
                        String jsonIn = joinTask.execute().get();

                        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, hh:mm:ss a").create();
                        ob = gson.fromJson(jsonIn, OssanBean.class);
                        JsonObject jsonObjectResp = gson.fromJson(jsonIn, JsonObject.class);
                        //顯示錯誤訊息
                        errorEmail = jsonObjectResp.get("errorEmail").getAsString();
                        errorPassword = jsonObjectResp.get("errorPassword").getAsString();
                        errorName = jsonObjectResp.get("errorName").getAsString();
                        errorPhone = jsonObjectResp.get("errorPhone").getAsString();
                        errorAddress = jsonObjectResp.get("errorAddress").getAsString();
                        errorNickName = jsonObjectResp.get("errorNickname").getAsString();
                    } catch (Exception e){
                        Log.d("join", e.toString());
                    }
                    //失敗
                    if (ob == null){
                        tvErrorEmail.setText(errorEmail);
                        tvErrorPassword.setText(errorPassword);
                        tvErrorName.setText(errorName);
                        tvErrorPhone.setText(errorPhone);
                        tvErrorAddress.setText(errorAddress);
                        tvErrorNickName.setText(errorNickName);
                        Common.showToast(getActivity(),"No join");
                        Navigation.findNavController(v).navigate(R.id.joinFragment);
                    } else {
                        //成功
                        Common.showToast(getActivity(),"Success join");
                        Navigation.findNavController(v).navigate(R.id.action_joinFragment_to_loginFragment);
                    }
                }
            }
        });
    }
}

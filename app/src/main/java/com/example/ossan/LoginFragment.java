package com.example.ossan;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.sql.Timestamp;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {
    private Activity activity;
    private BottomNavigationView bottomNavigationView;
    private Task loginTask;
    private String errorEmail, errorPassword = null;
    private EditText ediEmail, ediPassword;
    private TextView login_errorEmail,login_errorPassword;
    private Button facebook_login_button, google_login_button;
    CallbackManager callbackManager;
    AccessToken accessToken;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity.setTitle(R.string.login_name);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ediEmail = view.findViewById(R.id.fragment_login_ediEmail);
        ediPassword = view.findViewById(R.id.fragment_login_ediPassword);
        login_errorEmail = view.findViewById(R.id.fragment_login_errorEmail);
        login_errorPassword = view.findViewById(R.id.fragment_login_errorPassword);
        //Login 畫面要把buttomNavigationView 隱藏
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);
//---------------------------------------------------------------------------------
        //登入按鈕   觸發點擊就到Main Fragment
        Button btnLogin = view.findViewById(R.id.fragment_login_btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inputOK = false;
                String email = ediEmail.getText().toString().trim();
                String password = ediPassword.getText().toString().trim();
                if (ediEmail != null && ediPassword != null){
                    inputOK = true;
                }
                //如果填入的東西都不是空值才繼續做
                if (inputOK && Common.networkConnected(getActivity())){
//TODO
                    String url = Common.URL + "/loginAndroid";
                    OssanBean ossanBean = new OssanBean();

                    try {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("action","login");
                        jsonObject.addProperty("email",email);
                        jsonObject.addProperty("PASSWORD",password);
                        String jsonOut = jsonObject.toString();
                        loginTask = new Task(url, jsonOut);

                        String jsonIn = loginTask.execute().get();
                        JSONObject jsonObjectIn = new JSONObject(jsonIn);
                        // 有可能發生 jsonObjectIn.get  找不到member  json 的問題
                        try {
                            ossanBean.setOssanNo(jsonObjectIn.getInt("ossanNo"));
                            ossanBean.setEmail(jsonObjectIn.getString("email"));
                            ossanBean.setPassword(jsonObjectIn.getString("password"));
                            ossanBean.setName(jsonObjectIn.getString("name"));
                            ossanBean.setPhone(jsonObjectIn.getString("phone"));
                            ossanBean.setAddress(jsonObjectIn.getString("address"));
                            ossanBean.setNickname(jsonObjectIn.getString("nickname"));
                                Timestamp ts = null;
                                ts = Timestamp.valueOf(jsonObjectIn.getString("creationDateTime"));
                            ossanBean.setRegisterTime(ts);
                            ossanBean.setImageForAndroid(jsonObjectIn.getString("ImageForAndroid"));
                            ossanBean.setFBLogin(jsonObjectIn.getBoolean("isFBLogin"));
                            ossanBean.setGoogleLogin(jsonObjectIn.getBoolean("isGoogleLogin"));
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        //取得錯誤資訊
                            errorEmail = jsonObjectIn.getString("errorEmail");
                            errorPassword = jsonObjectIn.getString("errorPassword");
                    } catch (Exception e){
                        //失敗
                        Log.d("join", e.toString());
                    }
                    if (ossanBean.getOssanNo() == null){
                        login_errorEmail.setText(errorEmail);
                        Common.showToast(getActivity(),"error login");

                        login_errorPassword.setText(errorPassword);
                        Common.showToast(getActivity(),"error Password");
//                        Navigation.findNavController(v).navigate(R.id.loginFragment);
                        //成功
                    } else {
                        //加入會員資料到偏好設定裡
                        SharedPreferences spf = getActivity().getSharedPreferences(Common.PREF_FILE,MODE_PRIVATE);
                        spf.edit()
                                .putBoolean("login", true)
                                .putString("email", ossanBean.getEmail())
                                .putString("nickname", ossanBean.getNickname())
                                .putString("password", ossanBean.getPassword())

                        .apply();
                        getActivity().setResult(Activity.RESULT_OK);
                        login_errorEmail.setText("");
                        login_errorPassword.setText("");
                        Common.showToast(getActivity(),"Succsses login");
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homepageFragment);
                    }
                }
            }
        });

        Button btnJoin = view.findViewById(R.id.fragment_login_btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_joinFragment);
            }
        });

        //-----------------------------------------------------------------------------
        //facebook登入api設定
        //https://developers.facebook.com/docs/facebook-login/android?locale=zh_TW   （這裡有詳細指令的說明)
        callbackManager = CallbackManager.Factory.create();
        facebook_login_button = (LoginButton) view.findViewById(R.id.login_facebook);
        ((LoginButton) facebook_login_button).setReadPermissions("email");
        ((LoginButton) facebook_login_button).setFragment(this);
        facebook_login_button.setHeight(300);
        ((LoginButton) facebook_login_button).registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //成功時方法
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken =loginResult.getAccessToken();
                GraphRequest request =GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("FB", "complete");
                        Log.d("FB", object.optString("name"));
                        Log.d("FB", object.optString("link"));
                        Log.d("FB", object.optString("id"));
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homepageFragment);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();
                //FB登入成功時 就到Homepage Fragment
            }

            @Override
            public void onCancel() {
                Log.d("FB", "CANCEL");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FB", error.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    //    -----------------------------------------------------------------------------


    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences spf = getActivity().getSharedPreferences(Common.PREF_FILE, MODE_PRIVATE);
        boolean login = spf.getBoolean("login",false);
        if (login){
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homepageFragment);
        }
    }
}

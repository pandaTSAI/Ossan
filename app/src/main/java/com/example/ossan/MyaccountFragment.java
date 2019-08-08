package com.example.ossan;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ossan.common.Common;
import com.example.ossan.task.UrlPictureTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Context.MODE_PRIVATE;

public class MyaccountFragment extends Fragment {
    private Activity activity;
    private UrlPictureTask urlPictureTask;
    private ImageView fragment_myaccount_imgPersonal;
    private TextView fragment_myaccount_tvNickname;
    private BottomNavigationView bottomNavigationView;
    private Button fragment_myaccount_btnName,btSet,btOrder,btIntro,btOther,btLogout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity.setTitle(R.string.my_account_name);
        return inflater.inflate(R.layout.fragment_myaccount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        SharedPreferences spf = getActivity().getSharedPreferences(Common.PREF_FILE, MODE_PRIVATE);
        boolean login = spf.getBoolean("login",false);
        if (login) {
            String nickname = spf.getString("nickname","");
            String password = spf.getString("password","");
            String picturePath = spf.getString("ossanImage","");
            fragment_myaccount_tvNickname = view.findViewById(R.id.fragment_myaccount_tvNickname);
            fragment_myaccount_imgPersonal = view.findViewById(R.id.fragment_myaccount_imgPersonal);

            fragment_myaccount_tvNickname.setText(nickname);

//TODO
            String url = "http://10.0.2.2:8080/ossanRental" + picturePath;
            urlPictureTask = new UrlPictureTask(url , fragment_myaccount_imgPersonal);
            urlPictureTask.execute();
            //點擊到哪個button 就跳到哪個頁面
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    switch (v.getId()){
                        //查看個人資料
                        case R.id.fragment_myaccount_btnName:
                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_myaccountMainFragment);
                            break;
                        //設定
                        case R.id.btSet:
                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_settingFragment);
                            break;
                        //訂單細節
                        case R.id.btOrder:
                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_orderFragment);
                            break;
                        //自我介紹
                        case R.id.btIntro:
                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_introductionFragment);
                            break;
                        //其他
                        case R.id.btOther:
                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_otherFragment);
                            break;
                        //登出
                        case R.id.btLogout:
                            new AlertDialog.Builder(getActivity()).setMessage("確定要登出嗎").setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //登出後把偏好設定login 設為false
                                            SharedPreferences pref = getActivity().getSharedPreferences(Common.PREF_FILE, MODE_PRIVATE);
                                            pref.edit().putBoolean("login",false).apply();
                                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_loginFragment);
                                            Common.showToast(getActivity(),"登出");
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
//                            Navigation.findNavController(v).navigate(R.id.action_myaccountFragment_to_loginFragment);
                    }
                }
            };
            view.findViewById(R.id.fragment_myaccount_btnName).setOnClickListener(onClickListener);
            view.findViewById(R.id.btSet).setOnClickListener(onClickListener);
            view.findViewById(R.id.btOrder).setOnClickListener(onClickListener);
            view.findViewById(R.id.btIntro).setOnClickListener(onClickListener);
            view.findViewById(R.id.btOther).setOnClickListener(onClickListener);
            view.findViewById(R.id.btLogout).setOnClickListener(onClickListener);
        }
    }
}

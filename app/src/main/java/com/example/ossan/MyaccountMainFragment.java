package com.example.ossan;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyaccountMainFragment extends Fragment {
    private Activity activity;
    private BottomNavigationView bottomNavigationView;
    public static final String TAG = "MyaccountMain";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity.setTitle(R.string.my_account_name);
        return inflater.inflate(R.layout.fragment_myaccount_main, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        ImageView imageView = view.findViewById(R.id.fragment_myaccount_main_imageView);
        TextView tvname = view.findViewById(R.id.fragment_myaccount_main_tv_name);
        // 取得Bundle物件
        Bundle bundle = getArguments();
        Log.d(TAG,"get");
        // 如果Bundle不為null，進一步取得News物件
        Myaccount myaccounts = bundle == null ? null : (Myaccount) bundle.getSerializable("myaccounts");
        if(myaccounts == null){
            Log.d(TAG,"null");
        } else {
            int imageView1 = myaccounts.getImageId();
            imageView.setImageResource(imageView1);
            String tvname1 = myaccounts.getName();
            tvname.setText(tvname1);
        }
//
//        if (myaccounts != null) {
//            int imageView1 = myaccounts.getImageId();
//            imageView.setImageResource(imageView1);
//            String tvname1 = myaccounts.getName();
//            tvname.setText(tvname1);
//
//        } else {
//            Toast.makeText(getActivity(),R.string.textDataError,Toast.LENGTH_SHORT).show();
//        }
    }
}

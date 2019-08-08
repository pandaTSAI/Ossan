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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SingleProductFragment extends Fragment {
    private Activity activity;
    public static final String TAG = "SingleProduct";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity.setTitle(R.string.homepage_name);
        return inflater.inflate(R.layout.fragment_single_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ImageView imageView = view.findViewById(R.id.fragment_story_imageView);
        final TextView tvname = view.findViewById(R.id.fragment_story_tvname);
        TextView tvquote = view.findViewById(R.id.fragment_story_tvquote);
        final TextView tvprice = view.findViewById(R.id.fragment_story_tvprice);
        TextView tvstory = view.findViewById(R.id.fragment_story_tv1);
        // 取得Bundle物件
        final Bundle bundle = getArguments();
        // 如果Bundle不為null，進一步取得News物件
        final Ossans ossans = bundle == null ? null : (Ossans) bundle.getSerializable("ossans");
        if (ossans != null) {
            int imageView1 = ossans.getImageId();
            imageView.setImageResource(imageView1);
            String tvname1 = ossans.getName();
            tvname.setText(tvname1);
            String tvquote1 = ossans.getQuote();
            tvquote.setText(tvquote1);
            String tvprice1 = ossans.getPrice();
            tvprice.setText(tvprice1);
            String tvstory1 = ossans.getStory();
            tvstory.setText(tvstory1);

        } else {
            Toast.makeText(getActivity(), R.string.textDataError, Toast.LENGTH_SHORT).show();
        }

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //加入購物車
                    case R.id.fragment_story_btnshoppingcart:
//                        Bundle bundle = new Bundle();
//                        int imageView1 = ossans.getImageId();
//                        Log.d("TAG", String.valueOf(ossans.getImageId()));
//                        imageView.setImageResource(imageView1);
//
//
//                        String tvname1 = ossans.getName();
//                        Log.d("TAG", String.valueOf(ossans.getName()));
//                        tvname.setText(tvname1);
//
//
//                        String tvprice1 = ossans.getPrice();
//                        Log.d("TAG", String.valueOf(ossans.getPrice()));
//                        tvprice.setText(tvprice1);
//
//                        bundle.putSerializable("shops", ossans);
                        Navigation.findNavController(v).navigate(R.id.action_singleFragment_to_shoppingcartFragment);
                        break;
                    case R.id.fragment_story_btnpay:
                        Navigation.findNavController(v).navigate(R.id.action_singleFragment_to_shoppingcartFragment);
                        break;
                }
            }
        };
        view.findViewById(R.id.fragment_story_btnshoppingcart).setOnClickListener(onClickListener);
        view.findViewById(R.id.fragment_story_btnpay).setOnClickListener(onClickListener);
    }
}


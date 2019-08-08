package com.example.ossan;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SingleStoryFragment extends Fragment {
    private Activity activity;
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity.setTitle(R.string.story_name);
        return inflater.inflate(R.layout.fragment_single_story, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        ImageView imageView = view.findViewById(R.id.fragment_single_story_imageView);
        TextView tvname = view.findViewById(R.id.fragment_single_story_tvname);
        TextView tvquote = view.findViewById(R.id.fragment_single_story_tvquote);
        // 取得Bundle物件
        Bundle bundle = getArguments();
        // 如果Bundle不為null，進一步取得News物件
        Stories stories = bundle == null ? null : (Stories) bundle.getSerializable("stories");
        if (stories != null) {
            int imageView1 = stories.getImageId();
            imageView.setImageResource(imageView1);
            String tvname1 = stories.getName();
            tvname.setText(tvname1);
            String tvquote1 = stories.getQuote();
            tvquote.setText(tvquote1);

        } else {
            Toast.makeText(getActivity(),R.string.textDataError,Toast.LENGTH_SHORT).show();
        }
    }
}

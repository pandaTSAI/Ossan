package com.example.ossan;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment {
    private Activity activity;
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity.setTitle(R.string.story_name);
        return inflater.inflate(R.layout.fragment_story, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvStories = view.findViewById(R.id.fragment_story_recyclerView);
        rvStories.setLayoutManager(new GridLayoutManager(getActivity(),2));
        List<Stories> stories = getStoriesList();
        rvStories.setAdapter(new StoriesAdapter(getActivity(), stories));
        // GONE是隱藏 VISIBLE是顯現
        bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {
        Context context;
        List<Stories> storiesList;
        StoriesAdapter(Context context, List<Stories> storiesList) {
            this.context = context;
            this.storiesList = storiesList;
        }

        @Override
        public int getItemCount() {
            return storiesList.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView tv_name;
            TextView tv_quote;

            MyViewHolder(View itemView){
                super(itemView);
                imageView = itemView.findViewById(R.id.imgView_stories);
                tv_name = itemView.findViewById(R.id.tv_storiesname);
                tv_quote = itemView.findViewById(R.id.tv_storiesquote);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_view_stories,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final Stories stories = storiesList.get(position);
            holder.imageView.setImageResource(stories.getImageId());
            holder.tv_name.setText(stories.getName());
            holder.tv_quote.setText(stories.getQuote());
            // 點擊一筆資料會切換到下一頁
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("stories", stories);
                    Navigation.findNavController(v).navigate(R.id.action_storyFragment_to_singleStoryFragment, bundle);
                }
            });
        }
    }

    private List<Stories> getStoriesList() {
        List<Stories> stories = new ArrayList<>();
        stories.add(new Stories(R.drawable.uncle_image_1, "馬東石","人是善良的，群眾卻是殘酷的。"));
        stories.add(new Stories(R.drawable.uncle_image_2, "小林熏","所謂成熟 就是明明該哭該鬧 卻不言不語地微笑。"));
        stories.add(new Stories(R.drawable.uncle_image_3, "北野武","雖然辛苦我還是會選擇那種滾燙的人生。"));
        stories.add(new Stories(R.drawable.uncle_image_4, "老查","與其100%的人喜歡程度10%，不如讓10%的人100%程度喜歡。"));
        stories.add(new Stories(R.drawable.uncle_image_5, "陳道明","做人的最高境界是—節制。"));
        stories.add(new Stories(R.drawable.uncle_image_6, "葛優","你要想找一個帥哥就別來了，你要想找一個錢包就別見了。"));
        stories.add(new Stories(R.drawable.uncle_image_7, "柯文哲","你不解決問題，問題會解決你。"));
        stories.add(new Stories(R.drawable.uncle_image_8, "李立群","人生如戲，不怕爛戲，就怕沒戲。"));
        stories.add(new Stories(R.drawable.uncle_image_9, "甄子丹","所謂人器合一，心與意合，意與氣合，氣與力合。"));
        stories.add(new Stories(R.drawable.uncle_image_10, "史派西","若你有幸成功，就有責任拉抬下面的人。"));
        stories.add(new Stories(R.drawable.uncle_image_11, "梅西","我很記得我第一個足球的樣子，在我心裏，它就像一顆糖果。"));
        stories.add(new Stories(R.drawable.uncle_image_12, "宋唯農","他說雙手越血腥 銅臭味就越可愛。"));
        stories.add(new Stories(R.drawable.uncle_image_13, "真田廣之","他們是很有趣味的人他們一醒來便盡力將各個生活目標做至盡善盡美 我從未見過這樣嚴於律己的生活方式。"));
        stories.add(new Stories(R.drawable.uncle_image_14, "貝克漢","我不怕死，坐飛機我從不扣安全帶。"));
        stories.add(new Stories(R.drawable.uncle_image_15, "波維奇","品行與態度永遠淩駕於天賦之上。"));
        stories.add(new Stories(R.drawable.uncle_image_16, "史塔克","敵人都是自己創造出來的。"));
        stories.add(new Stories(R.drawable.uncle_image_17, "尚雷諾","人生總是那麼痛苦嗎？還是只有小時候是這樣？ 總是如此。"));
        stories.add(new Stories(R.drawable.uncle_image_18, "渡邊謙","你想豁出去試一試？還是成為內心充滿悔恨的老人，孤獨地死去？"));
        return stories;
    }


}

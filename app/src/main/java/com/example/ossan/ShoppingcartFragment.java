package com.example.ossan;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShoppingcartFragment extends Fragment implements CartAdapter.RefreshPriceInterface,View.OnClickListener {
    private Activity activity;
    public static final String TAG = "Shoppingcart";

    private ListView listView;
    private CheckBox shoppingcart_chekbox;
    private TextView shoppingcart_price;
    private TextView shoppingcart_delete;
    private TextView shoppingcart_pay;

    private CartAdapter adapter;

    private double totalPrice = 0.0;
    private int totalCount = 0;
    private List<HashMap<String,String>> shopsList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        activity.setTitle(R.string.shopping_cart);
        return inflater.inflate(R.layout.fragment_shoppingcart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imgView = view.findViewById(R.id.item_shops_img);
        TextView tv_name = view.findViewById(R.id.item_shops_name);
        TextView tv_price = view.findViewById(R.id.item_shops_price);

//        final Bundle bundle = getArguments();
//        // 如果Bundle不為null，進一步取得News物件
//        final Ossans shops = bundle == null ? null : (Ossans) bundle.getSerializable("shops");
//        if (shops != null) {
//            int imageView = shops.getImageId();
//            Log.d("TAG", String.valueOf(shops.getImageId()));
////            imgView.setImageResource(imageView);
//
//            String tvname = shops.getName();
//            Log.d("TAG",String.valueOf(shops.getName()));
////            tv_name.setText(tvname);
//
//            String tvprice = shops.getPrice();
//            Log.d("TAG",String.valueOf(shops.getPrice()));
////            tv_price.setText(tvprice);
//        } else {
//            Toast.makeText(getActivity(), R.string.textDataError, Toast.LENGTH_SHORT).show();
//        }

        initDate();
//        initView();

    }

    //控制價格展示
    private void priceControl(Map<String, Integer> dataMap){
        totalCount = 0;
        totalPrice = 0.0;
        for(int i=0;i<shopsList.size();i++){
            if(dataMap.get(shopsList.get(i).get("id"))==1){
                totalCount=totalCount+Integer.valueOf(shopsList.get(i).get("count"));
                double Price=Integer.valueOf(shopsList.get(i).get("count"))*Double.valueOf(shopsList.get(i).get("price"));
                totalPrice=totalPrice+Price;
            }
        }
        shoppingcart_price.setText("$ "+totalPrice);
        shoppingcart_pay.setText("付款("+totalCount+")");
    }
    @Override
    public void refreshPrice(Map<String, Integer> dataMap) {
        priceControl(dataMap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shoppingcart_chekbox:
                AllTheSelected();
                break;
            case (R.id.shoppingcart_pay):
                if(totalCount<=0){
                    Toast.makeText(getActivity(),"請選擇要付款的商品",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(),"付款",Toast.LENGTH_SHORT).show();
            case (R.id.shoppingcart_delete):
                if(totalCount<=0){
                    Toast.makeText(getActivity(),"請選擇要刪除的商品",Toast.LENGTH_SHORT).show();
                    return;
                }
                checkDelete(adapter.getdataMap());
                break;
        }
    }

    //刪除操作
    private void checkDelete(Map<String,Integer> map){
        List<HashMap<String,String>> waitDeleteList=new ArrayList<>();
        Map<String,Integer> waitDeleteMap =new HashMap<>();
        for(int i=0;i<shopsList.size();i++){
            if(map.get(shopsList.get(i).get("id"))==1){
                waitDeleteList.add(shopsList.get(i));
                waitDeleteMap.put(shopsList.get(i).get("id"),map.get(shopsList.get(i).get("id")));
            }
        }
        shopsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }

    //全選或反選
    private void AllTheSelected(){
        Map<String,Integer> map=adapter.getdataMap();
        boolean isCheck=false;
        boolean isUnCheck=false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(Integer.valueOf(entry.getValue().toString())==1)
                isCheck=true;
            else isUnCheck=true;
        }
        if(isCheck==true&&isUnCheck==false){//已經全選做反選
            for(int i=0;i<shopsList.size();i++){
                map.put(shopsList.get(i).get("id"),0);
            }
            shoppingcart_chekbox.setChecked(false);
        }else if(isCheck==true && isUnCheck==true){//部分選擇做全選
            for(int i=0;i<shopsList.size();i++){
                map.put(shopsList.get(i).get("id"),1);
            }
            shoppingcart_chekbox.setChecked(true);
        }else if(isCheck==false && isUnCheck==true){//一個沒選做全選
            for(int i=0;i<shopsList.size();i++){
                map.put(shopsList.get(i).get("id"),1);
            }
            shoppingcart_chekbox.setChecked(true);
        }
        priceControl(map);
        adapter.setdataMap(map);
        adapter.notifyDataSetChanged();
    }

    private void initView(){
        listView = (ListView) getActivity().findViewById(R.id.fragment_shoppingcart_recyclerView);
        shoppingcart_chekbox = (CheckBox) getActivity().findViewById(R.id.shoppingcart_chekbox);
        shoppingcart_price = (TextView) getActivity().findViewById(R.id.shoppingcart_price);
        shoppingcart_delete = (TextView) getActivity().findViewById(R.id.shoppingcart_delete);
        shoppingcart_pay = (TextView) getActivity().findViewById(R.id.shoppingcart_pay);
        shoppingcart_pay.setOnClickListener(this);
        shoppingcart_delete.setOnClickListener(this);
        shoppingcart_chekbox.setOnClickListener(this);

        adapter=new CartAdapter(getActivity(),shopsList);
        adapter.setRefreshPriceInterface(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initDate(){
        shopsList=new ArrayList<>();
        for(int i=0;i<1;i++){
            HashMap<String,String> map=new HashMap<>();
            map.put("id",R.drawable.uncle_image_1+"");
            map.put("name","馬東石");
            map.put("price",300+"");
            map.put("count",1+"");
            shopsList.add(map);
        }
        initView();
    }
}


package com.example.ossan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<HashMap<String,String>> data;
    private ViewHolder viewholder;
    private Map<String,Integer> dataMap;
    private RefreshPriceInterface refreshPriceInterface;

    public CartAdapter(Context context,List<HashMap<String,String>> list){
        this.context=context;
        this.data=list;

        dataMap=new HashMap<>();
        for(int i=0;i<data.size();i++){
            dataMap.put(data.get(i).get("id"),0);
        }
    }

    public View getView(final int position, View view, ViewGroup viewGroup) {
        viewholder=new ViewHolder();
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_view_shops,null);

            viewholder.checkBox=(CheckBox)view.findViewById(R.id.item_shops_checkbox);
            viewholder.icon=(ImageView)view.findViewById(R.id.item_shops_img);
            viewholder.name=(TextView)view.findViewById(R.id.item_shops_name);
            viewholder.price=(TextView)view.findViewById(R.id.item_shops_price);
            viewholder.num=(TextView)view.findViewById(R.id.item_shops_num);
            viewholder.reduce=(TextView)view.findViewById(R.id.item_shops_reduce);
            viewholder.add=(TextView)view.findViewById(R.id.item_shops_add);

            view.setTag(viewholder);
        }else {
            viewholder=(ViewHolder)view.getTag();
        }

        if(data.size()>0){

            if(dataMap.get(data.get(position).get("id"))==0)viewholder.checkBox.setChecked(false);
            else viewholder.checkBox.setChecked(true);
            HashMap<String,String> map=data.get(position);
            viewholder.name.setText(map.get("name"));
            viewholder.num.setText(map.get("count"));
            viewholder.price.setText("$ "+(Double.valueOf(map.get("price")) * Integer.valueOf(map.get("count"))));

            viewholder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    if(((CheckBox)view).isChecked())dataMap.put(data.get(index).get("id"),1);
                    else dataMap.put(data.get(index).get("id"),0);
                    refreshPriceInterface.refreshPrice(dataMap);
                }
            });
            viewholder.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    data.get(index).put("count",(Integer.valueOf(data.get(index).get("count"))-1)+"");
                    if(Integer.valueOf(data.get(index).get("count"))<=0){
                        String deID=data.get(index).get("id");
                        data.remove(index);
                        dataMap.remove(deID);
                    }
                    notifyDataSetChanged();
                    refreshPriceInterface.refreshPrice(dataMap);
                }
            });
            viewholder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    if(Integer.valueOf(data.get(index).get("count"))==15){
                        //15為庫存可選擇上限
                        Toast.makeText(context,"已達上限",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    data.get(index).put("count",(Integer.valueOf(data.get(index).get("count"))+1)+"");
                    notifyDataSetChanged();
                    refreshPriceInterface.refreshPrice(dataMap);
                }
            });
        }

        return view;
    }

    public Map<String,Integer> getdataMap(){
        return dataMap;
    }
    public void setdataMap(Map<String,Integer> dataMap){
        this.dataMap=new HashMap<>();
        this.dataMap.putAll(dataMap);
    }

    public interface RefreshPriceInterface{
        void refreshPrice(Map<String,Integer> dataMap);
    }
    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface){
        this.refreshPriceInterface=refreshPriceInterface;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    class ViewHolder{
        CheckBox checkBox;
        ImageView icon;
        TextView name,price,num,reduce,add;
    }
}

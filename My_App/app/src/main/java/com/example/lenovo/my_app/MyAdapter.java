package com.example.lenovo.my_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/4/12.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private boolean flag;
    //创建一个存放下标的集合
    private List<Integer> index = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private OnClickedChecked onClickedChecked;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        //清空集合并刷新适配器
        index.clear();
        data.clear();
        notifyDataSetChanged();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        notifyDataSetChanged();
    }

    public void setOnClickedChecked(OnClickedChecked onClickedChecked) {
        this.onClickedChecked = onClickedChecked;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.che_box = convertView.findViewById(R.id.che_box);
            holder.text_view = convertView.findViewById(R.id.text_view);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text_view.setText(list.get(position));
        //取出在User对象里设置的结果
        holder.che_box.setChecked(new User().isChecked());
        //多选框的点击监听
        holder.che_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否选中，如果选中把下标添加到index集合中，并且把数据添加到data集合中。
                if (((CheckBox)v).isChecked()){
                    index.add(position);
                    data.add(list.get(position));
                }else {
                    //遍历index集合，判断是否用户把选中的chebox点击为未选中，如果找到了的话，进行删除。
                    for (int i = 0; i < index.size() ; i++){
                        if (position == index.get(i)){
                            index.remove(i);
                            data.remove(i);
                        }
                    }
                }
                //接口
                onClickedChecked.onItemChecked(data);
            }
        });
        //判断显示删除按钮和多选框
        if (flag){
            //显示
            holder.che_box.setVisibility(View.VISIBLE);
        }else {
            //隐藏
            holder.che_box.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        private CheckBox che_box;
        private TextView text_view;
    }
    //自定义接口
    public interface OnClickedChecked{
        void onItemChecked(List<String> data);
    }
}

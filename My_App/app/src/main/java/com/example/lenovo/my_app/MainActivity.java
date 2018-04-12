package com.example.lenovo.my_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.OnClickedChecked {

    /**
     * 编辑
     */
    private TextView mTextView;
    private ListView mListView;
    /**
     * 删除
     */
    private Button mBtnDel;
    private List<String> list;
    //初始值为false
    private boolean flag = false;
    private MyAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //赋值
        for (int i = 0; i < 20 ; i++){
            list.add("第" + i + "条目");
        }
        //设置数据
        adapter.setList(list);
        //判断点击编辑按钮
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为true编辑变为完成 否则 为编辑
                if (!flag){
                    mTextView.setText("完成");
                    mBtnDel.setVisibility(View.VISIBLE);
                }else {
                    mTextView.setText("编辑");
                    mBtnDel.setVisibility(View.GONE);
                }
                //传入到适配器，设置适配器中的flag
                adapter.setFlag(flag);
            }
        });
        //设置接口
        adapter.setOnClickedChecked(this);
        //设置适配器
        mListView.setAdapter(adapter);
    }

    private void initView() {
        //找控件
        mTextView = (TextView) findViewById(R.id.text_view);
        mListView = (ListView) findViewById(R.id.list_view);
        mBtnDel = (Button) findViewById(R.id.btn_del);
        mBtnDel.setOnClickListener(this);
        //初始化
        list = new ArrayList<>();
        data = new ArrayList<>();
        //创建适配器
        adapter = new MyAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_del:
                //点击删除按钮进行判断
                if (data.size() == 0){
                    Toast.makeText(this,"请选择要删除的条目.",Toast.LENGTH_LONG).show();
                }else {
                    //遍历data和list集合，取出data集合中的数据与list集合中的数据进行比较，如果数据相同，通过下标进行删除
                    for (int i = 0; i < data.size() ; i++){
                        for (int j = 0; j < list.size() ; j++){
                            if (data.get(i).equals(list.get(j))){
                                list.remove(j);
                            }
                        }
                    }
                    data.clear();
                    //把选中的chebox设置为false
                    new User().setChecked(false);
                    //重新设置数据
                    adapter.setList(list);
                    Toast.makeText(this,"删除成功.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onItemChecked(List<String> data) {
        //通过接口接收数据
        this.data = data;
    }
}

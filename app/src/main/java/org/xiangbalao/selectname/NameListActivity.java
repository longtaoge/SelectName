package org.xiangbalao.selectname;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.xiangbalao.common.Constant;
import org.xiangbalao.selectname.adapter.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NameListActivity extends Activity implements OnClickListener {


    private TextView tv_name1;
    private TextView tv_name2;
    private TextView tvLayoutName;


    private String name1;

    private String name2;

    private String layoutName;
    private TextView tvlayoutName;

    private RecyclerView rc_namelist;
    private RecyclerView.Adapter recycleAdapter;


    private TreeMap<String, String> nameMap = new TreeMap<>();


    private List<String> nameList = new ArrayList<>();
    private ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        initView();


        name1 = Hawk.get(Constant.NAME1, null);

        name2 = Hawk.get(Constant.NAME2, null);

        layoutName = Hawk.get(Constant.LAYOUT, null);


        if (name1 != null && name2 != null) {


            String[] name1s = name1.split("、");


            String[] name2s = name2.split("、");


            for (int i = 0; i < name1s.length; i++) {


                for (int j = 0; j < name2s.length; j++) {


                    String name = name1s[i].trim() + name2s[j].trim();

                    nameMap.put(name.trim(), name.trim());


                }


            }


            if (nameMap != null) {


                for (Map.Entry<String, String> entry : nameMap.entrySet()) {


                    nameList.add(entry.getKey());

                }


            }


        }


        recycleAdapter = new MyRecyclerAdapter(NameListActivity.this, nameList);


        tv_name1.setText(String.valueOf(name1));
        tv_name2.setText(String.valueOf(name2));
        tvLayoutName.setText(String.valueOf(layoutName));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rc_namelist.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        rc_namelist.setAdapter(recycleAdapter);

        //设置增加或删除条目的动画
        rc_namelist.setItemAnimator(new DefaultItemAnimator());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back:

                onBackPressed();
                break;


        }

    }


    private void initView() {
        tv_name1 = (TextView) findViewById(R.id.tv_name1);
        tv_name2 = (TextView) findViewById(R.id.tv_name2);
        tvLayoutName = (TextView) findViewById(R.id.tvlayoutName);
        tvlayoutName = (TextView) findViewById(R.id.tvlayoutName);
        tvlayoutName.setOnClickListener(this);

        rc_namelist = (RecyclerView) findViewById(R.id.rc_namelist);
        rc_namelist.setOnClickListener(this);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }
}

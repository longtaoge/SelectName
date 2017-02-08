package org.xiangbalao.selectname.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import org.xiangbalao.common.Constant;
import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.base.BaseActivity;
import org.xiangbalao.common.weight.NumberItem;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;

import java.sql.SQLException;
import java.util.List;


public class NameDetailActivity extends BaseActivity implements OnClickListener {


    private TextView tvTextView;

    private Dao<Number, String> numberDao;


    private Dao<Word, String> wordDao;

    private DatabaseHelper helper;


    private String TAG = NameDetailActivity.class.getName();


    private String name;
    private NumberItem numberitem_1;
    private NumberItem numberitem_2;
    private NumberItem numberitem_3;
    private NumberItem numberitem_4;
    private NumberItem numberitem_5;


    private String fristName;
    private String secendName;
    private String thirdName;


    private int fristBihua;
    private int secendBihua;
    private int thirdBihua;
    private TextView tv_name;
    private ImageView btn_back;


    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_detail);
        helper = DatabaseHelper.getInstance(NameDetailActivity.this);

        try {
            numberDao = helper.getDao(Number.class);
            wordDao = helper.getDao(Word.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initView();

        name = getIntent().getStringExtra(Constant.NAMES);

        tv_name.setText(name);

        char[] names = name.toCharArray();

        if (names != null && names.length >= 2) {

            fristName = String.valueOf(names[0]);
            secendName = String.valueOf(names[1]);
            if (names.length > 2) {
                thirdName = String.valueOf(names[2]);
            }

        }


        try {
            List<Word> wordList1 = wordDao.queryForEq("simplified", fristName);
            List<Word> wordList2 = wordDao.queryForEq("simplified", secendName);
            List<Word> wordList3 = wordDao.queryForEq("simplified", thirdName);


            if (wordList1.size() > 0) {
                fristBihua = wordList1.get(0).getNumber();
            } else {
                ToastUtils.e("暂无 " + fristName + " 数据").show();
                LogUtils.i(NameDetailActivity.class.getSimpleName(), fristName);

            }


            if (wordList2.size() > 0) {
                secendBihua = wordList2.get(0).getNumber();

            } else {
                LogUtils.i(NameDetailActivity.class.getSimpleName(), secendName);
                ToastUtils.e("暂无 " + secendName + " 数据").show();


            }


            if (wordList3.size() > 0) {
                thirdBihua = wordList3.get(0).getNumber();
            } else {
                LogUtils.i(NameDetailActivity.class.getSimpleName(), thirdName);
                ToastUtils.e("暂无 " + thirdName + " 数据").show();

            }


        } catch (Exception e) {
            e.printStackTrace();


            ToastUtils.e(e.toString() + " " + name).show();
        }


        int number1 = fristBihua + secendBihua + thirdBihua;
        int number2 = fristBihua + 1;
        int number3 = thirdBihua + secendBihua;
        int number4 = fristBihua + secendBihua;
        int number5 = thirdBihua + 1;


        try {
            List<Number> numberList1 = numberDao.queryForEq("number", number1);
            List<Number> numberList2 = numberDao.queryForEq("number", number2);
            List<Number> numberList3 = numberDao.queryForEq("number", number3);
            List<Number> numberList4 = numberDao.queryForEq("number", number4);
            List<Number> numberList5 = numberDao.queryForEq("number", number5);


            Number n1 = numberList1.get(0);
            Number n2 = numberList2.get(0);
            Number n3 = numberList3.get(0);
            Number n4 = numberList4.get(0);
            Number n5 = numberList5.get(0);

            numberitem_1.setTitle("总格(" + number1 + ") " + n1.getJixiong());
            numberitem_2.setTitle("天格(" + number2 + ") " + n2.getJixiong());
            numberitem_3.setTitle("地格(" + number3 + ") " + n3.getJixiong());
            numberitem_4.setTitle("人格(" + number4 + ") " + n4.getJixiong());
            numberitem_5.setTitle("外格(" + number5 + ") " + n5.getJixiong());


            numberitem_1.setDes(n1.getLishu_miaoshu());
            numberitem_2.setDes(n2.getLishu_miaoshu());
            numberitem_3.setDes(n3.getLishu_miaoshu());

            numberitem_4.setDes(n4.getLishu_miaoshu());
            numberitem_5.setDes(n5.getLishu_miaoshu());


        } catch (SQLException e) {
            e.printStackTrace();
        }


        LogUtils.i(TAG, name);


    }


    private void initView() {


        tvTextView = (TextView) findViewById(R.id.tv);
        tvTextView.setFocusable(true);

        numberitem_1 = (NumberItem) findViewById(R.id.numberitem_1);
        numberitem_1.setOnClickListener(this);
        numberitem_2 = (NumberItem) findViewById(R.id.numberitem_2);
        numberitem_2.setOnClickListener(this);
        numberitem_3 = (NumberItem) findViewById(R.id.numberitem_3);
        numberitem_3.setOnClickListener(this);
        numberitem_4 = (NumberItem) findViewById(R.id.numberitem_4);
        numberitem_4.setOnClickListener(this);
        numberitem_5 = (NumberItem) findViewById(R.id.numberitem_5);
        numberitem_5.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setOnClickListener(this);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_back:

                onBackPressed();
                break;


        }


    }


}

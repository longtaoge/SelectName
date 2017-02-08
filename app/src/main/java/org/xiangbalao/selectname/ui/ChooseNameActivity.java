package org.xiangbalao.selectname.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.hawk.Hawk;

import org.xiangbalao.common.Constant;
import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.adapter.MyFiveLayoutAdapter;
import org.xiangbalao.selectname.base.BaseActivity;
import org.xiangbalao.selectname.model.FiveLayout;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;
import org.xiangbalao.selectname.utils.MyThread;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ChooseNameActivity extends BaseActivity
        implements View.OnClickListener {
    private String TAG = "ChooseNameActivity";
    private Button bt_test;
    private AppCompatEditText etFirstName;
    private AppCompatEditText lastname;

    private ImageView btn_back;
    private DatabaseHelper helper;
    private Dao<Word, String> wordDao;
    private Dao<Number, String> numberDao;

    private FiveLayout mFiveLayout;
    private TreeMap<String, FiveLayout> fiveLahoutMap;

    private RecyclerView rc_namelist;

    private List<FiveLayout> layoutList;


    private MyFiveLayoutAdapter recycleAdapter;


    @Override
    protected void initData() {
        mFiveLayout = new FiveLayout();
        fiveLahoutMap = new TreeMap<>();
        layoutList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_choose_name);
        initView();


    }


    /**
     * 初始化
     */
    private void initView() {


        bt_test = (Button) findViewById(R.id.bt_test);
        bt_test.setOnClickListener(this);
        etFirstName = (AppCompatEditText) findViewById(R.id.etFirstName);
        etFirstName.setOnClickListener(this);
        lastname = (AppCompatEditText) findViewById(R.id.lastname);
        lastname.setOnClickListener(this);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        rc_namelist = (RecyclerView) findViewById(R.id.rc_namelist);


        recycleAdapter = new MyFiveLayoutAdapter(ChooseNameActivity.this, layoutList);
        recycleAdapter.setItemClickListener(new MyFiveLayoutAdapter.ItemClickListener() {
            @Override
            public void onClick(int position) {



            //    Hawk.put(Constant.NAME1, layoutList.get(position));

                Intent mIntent = new Intent(ChooseNameActivity.this, NameList1Activity.class);


                mIntent.putExtra(Constant.LAYOUTLIST,layoutList.get(position));

                startActivity(mIntent);

            }
        });


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
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onResume() {


        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_test:
                if (attest()) {
                    dialog.show();

                    MyThread mThread = new MyThread();

                    mThread.setThreadListener(new MyThread.MyThreadListener() {
                        @Override
                        public void onThreadRun() {


                            calculate(ChooseNameActivity.this);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                }
                            });


                        }
                    });


                    mThread.start();


                }

                break;
            case R.id.bt_des:
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    private void calculate(Context context) {
        try {
            helper = DatabaseHelper.getInstance(context);
            wordDao = helper.getDao(Word.class);
            List<Word> words = wordDao.queryForEq("simplified", etFirstName.getText().toString().trim());


            //   LogUtils.i(TAG,  etFirstName.getText().toString().trim() + "：笔画  " + mFiveLayout.getFirstBihua() + "   天格 " + mFiveLayout.getTiange());


            if (words.size() > 0) {
                Word temWord = words.get(0);


                //
                for (int sencendWord = 1; sencendWord <= 30; sencendWord++) {
                    for (int thirdWord = 1; thirdWord <= 30; thirdWord++) {
                        //  LogUtils.i(TAG, temWord + "：笔画  " + mFiveLayout.getFirstBihua() + "   天格 " + mFiveLayout.getTiange());
                        mFiveLayout = new FiveLayout();
                        //总格
                        mFiveLayout.setZhongge(temWord.getNumber() + sencendWord + thirdWord);
                        //天格
                        mFiveLayout.setTiange(temWord.getNumber() + 1);
                        //地格
                        mFiveLayout.setDige(sencendWord + thirdWord);
                        //人格
                        mFiveLayout.setRenge(temWord.getNumber() + sencendWord);
                        //外格
                        mFiveLayout.setWaige(thirdWord + 1);

                        // TODO 检查吉凶

                        mFiveLayout.setFirstBihua(temWord.getNumber());
                        mFiveLayout.setSecondBihua(sencendWord);
                        mFiveLayout.setThirdBihua(thirdWord);


                        checkLuck(mFiveLayout);


                    }

                }


                if (fiveLahoutMap != null) {
                    if (fiveLahoutMap != null) {
                        for (Map.Entry<String, FiveLayout> entry : fiveLahoutMap.entrySet()) {
                            layoutList.add(entry.getValue());

                        }

                    }

                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recycleAdapter.setmDatas(layoutList);
                        recycleAdapter.notifyDataSetChanged();

                    }
                });


            } else {

                showToast("暂无" + etFirstName.getText().toString() + "五格数据");

            }


        } catch (SQLException e) {
            e.printStackTrace();


            showToast("数据库错误" + e.toString());


        }
    }


    /**
     * 检查吉凶
     *
     * @param mFiveLayout
     */
    private void checkLuck(FiveLayout mFiveLayout) {
        try {
            numberDao = helper.getDao(Number.class);
            //天格   姓氏 无法改变
            //总格
            List<Number> numberList4Zhongge = numberDao.queryForEq("number", mFiveLayout.getZhongge());
            //地格
            List<Number> numberList4Dige = numberDao.queryForEq("number", mFiveLayout.getDige());
            //人格
            List<Number> numberList4Renge = numberDao.queryForEq("number", mFiveLayout.getRenge());
            //外格
            List<Number> numberList4Waige = numberDao.queryForEq("number", mFiveLayout.getWaige());


            if (numberList4Zhongge.size() > 0 && numberList4Dige.size() > 0 && numberList4Renge.size() > 0 && numberList4Waige.size() > 0) { // 总格合法；

                if (numberList4Zhongge.get(0).getJixiong().contains("大吉")
                        && numberList4Dige.get(0).getJixiong().contains("大吉")
                        && numberList4Renge.get(0).getJixiong().contains("大吉")
                        && numberList4Waige.get(0).getJixiong().contains("大吉")) {
                    // 外格受姓氏限制无法改变，其它全吉 则为吉
                    StringBuilder layoutKey = new StringBuilder();
                    layoutKey.append(mFiveLayout.getFirstBihua())
                            .append("画-")
                            .append(mFiveLayout.getSecondBihua())
                            .append("画-")
                            .append(mFiveLayout.getThirdBihua());

                    fiveLahoutMap.put(layoutKey.toString(), mFiveLayout);

                    LogUtils.i(TAG, layoutKey.toString());

                    StringBuilder wuge = new StringBuilder();

                    wuge.append("总格-")
                            .append(mFiveLayout.getZhongge())
                            .append(" 天格-")
                            .append(mFiveLayout.getTiange())
                            .append(" 地格-")
                            .append(mFiveLayout.getDige())
                            .append(" 人格-")
                            .append(mFiveLayout.getRenge())
                            .append(" 外格-")
                            .append(mFiveLayout.getWaige());

                    LogUtils.i(TAG, wuge.toString());


                }


            }


        } catch (SQLException e) {
            e.printStackTrace();
            showToast("checkLuck 数据库错误" + e.toString());
        }


    }

    private void showToast(final String content) {

        LogUtils.i(TAG, content.toString());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.w(content).show();
            }
        });


    }


    protected boolean attest() {

        // validate
        String etFirstNameString = etFirstName.getText().toString().trim();
        if (TextUtils.isEmpty(etFirstNameString)) {
            ToastUtils.e("本版本只支持单字姓氏,请输入单字姓氏").show();
            return false;
        }


        return true;
    }


}

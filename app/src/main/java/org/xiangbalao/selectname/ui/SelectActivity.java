package org.xiangbalao.selectname.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.xiangbalao.common.Constant;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.base.BaseActivity;
import org.xiangbalao.selectname.utils.DataUtil;

import java.io.IOException;


public class SelectActivity extends BaseActivity implements OnClickListener {

    private final DataUtil dataUtil = new DataUtil();
    private Button btnSave;

    private Button btndel;

    private Button btnupdate;

    private Button btnQuery;

    private TextView tvTextView;

    private Intent mIntent;


    private String TAG = SelectActivity.class.getName();
    private AppCompatEditText etFirstName;
    private ImageView btn_back;

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_name);
        initView();
        etFirstName.setText("魏");
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


    public class MyThead extends Thread {
        @Override
        public void run() {
            super.run();
            //  dataUtil.initDb(SelectActivity.this);
            // dataUtil.initWord(SelectActivity.this);

            try {
                dataUtil.copyDataBase(SelectActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void initView() {

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btndel = (Button) findViewById(R.id.btndel);
        btndel.setOnClickListener(this);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(this);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        btnQuery.setOnClickListener(this);
        tvTextView = (TextView) findViewById(R.id.tv);
        tvTextView.setFocusable(true);

        etFirstName = (AppCompatEditText) findViewById(R.id.etFirstName);
        etFirstName.setOnClickListener(this);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mIntent = new Intent(SelectActivity.this, NameList1Activity.class);


        Hawk.put(Constant.FRISTNAME, etFirstName.getText().toString().trim());

        switch (v.getId()) {
            case R.id.btnSave:
                if (attest()) {
                    layout1();
                    startActivity(mIntent);
                }
                break;
            case R.id.btndel:
                if (attest()) {
                    layout2();
                    startActivity(mIntent);
                }
                break;
            case R.id.btnupdate:
                if (attest()) {
                    layout3();
                    startActivity(mIntent);
                }
                break;
            case R.id.btnQuery:
                if (attest()) {
                    layout4();
                    startActivity(mIntent);
                }
                break;
            case R.id.btn_back:
                onBackPressed();

                break;
            default:

                break;
        }

    }

    private void layout1() {
        String name11 = getString(R.string.name_11);
        String name12 = getString(R.string.name_12);
        Log.i(TAG, name11);
        Log.i(TAG, name12);


        Hawk.put(Constant.LAYOUT, "格局1");
        Hawk.put(Constant.NAME1, name11);
        Hawk.put(Constant.NAME2, name12);
    }


    private void layout2() {
        String name_21 = getString(R.string.name_21);
        String name_22 = getString(R.string.name_22);
        Log.i(TAG, name_21);
        Log.i(TAG, name_22);

        Hawk.put(Constant.LAYOUT, "格局2");
        Hawk.put(Constant.NAME1, name_21);
        Hawk.put(Constant.NAME2, name_22);


    }


    private void layout3() {
        String name_31 = getString(R.string.name_31);
        String name_32 = getString(R.string.name_32);
        Log.i(TAG, name_31);
        Log.i(TAG, name_32);

        Hawk.put(Constant.LAYOUT, "格局3");
        Hawk.put(Constant.NAME1, name_31);
        Hawk.put(Constant.NAME2, name_32);
    }


    private void layout4() {
        String name_41 = getString(R.string.name_41);
        String name_42 = getString(R.string.name_42);
        Log.i(TAG, name_41);
        Log.i(TAG, name_42);

        Hawk.put(Constant.LAYOUT, "格局4");
        Hawk.put(Constant.NAME1, name_41);
        Hawk.put(Constant.NAME2, name_42);
    }


}

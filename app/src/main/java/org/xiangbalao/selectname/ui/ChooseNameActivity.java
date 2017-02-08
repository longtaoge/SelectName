package org.xiangbalao.selectname.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xiangbalao.common.Constant;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.selectname.R;


public class ChooseNameActivity extends AppCompatActivity
        implements View.OnClickListener {


    private String TAG = "ChooseNameActivity";
    private Button bt_test;
    private AppCompatEditText etFirstName;
    private AppCompatEditText lastname;
    private Button bt_des;
    private ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        bt_des = (Button) findViewById(R.id.bt_des);
        bt_des.setOnClickListener(this);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
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
                    Intent mIntent = new Intent(ChooseNameActivity.this, NameDetailActivity.class);
                    mIntent.putExtra(Constant.NAMES, etFirstName.getText().toString().trim() + lastname.getText().toString().trim());
                    startActivity(mIntent);

                }

                break;
            case R.id.bt_des:
            case R.id.btn_back:
                onBackPressed();
                break;
        }
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

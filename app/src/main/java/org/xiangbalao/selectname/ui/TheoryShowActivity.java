package org.xiangbalao.selectname.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.base.BaseActivity;
import org.xiangbalao.selectname.utils.DataUtil;


public class TheoryShowActivity extends BaseActivity implements OnClickListener {

    private final DataUtil dataUtil = new DataUtil();


    private String TAG = TheoryShowActivity.class.getName();
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theory_show);
        initView();


    }


    private void initView() {


        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back:

                onBackPressed();
                break;

            default:
                break;
        }


    }


}

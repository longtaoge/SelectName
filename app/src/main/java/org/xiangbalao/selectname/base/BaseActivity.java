package org.xiangbalao.selectname.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.xiangbalao.common.toast.ToastUtils;

/**
 * Created by longtaoge on 17/2/6.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initDB();

    }


    private void initDB() {
        String databasesPath =
                //Environment.getExternalStorageDirectory()
                //.getAbsolutePath() +
                "hawkDome.db";

        Hawk.init(this)
                .setStorage(
                        HawkBuilder.newSharedPrefStorage(this
                        )).setCallback(new HawkBuilder.Callback() {

            @Override
            public void onSuccess() {

                ToastUtils.i("数据库创建成功").show();

            }

            @Override
            public void onFail(Exception e) {
                ToastUtils.e("数据库创建失败").show();

            }
        }).build();


    }


}

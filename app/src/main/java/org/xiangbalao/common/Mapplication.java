package org.xiangbalao.common;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.xiangbalao.common.toast.ToastUtils;

/**
 * Created by longtaoge on 17/2/1.
 */

public class Mapplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        initDB();

        ToastUtils.init(this);


    }


    private void initDB() {
        String databasesPath =
                //Environment.getExternalStorageDirectory()
                //.getAbsolutePath() +
                "hawkDome.db";

        Hawk.init(this)
                .setStorage(
                        HawkBuilder.newSqliteStorage(this,
                                databasesPath)).setCallback(new HawkBuilder.Callback() {

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

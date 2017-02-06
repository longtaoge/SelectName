package org.xiangbalao.selectname.app;

import android.app.Application;
import android.os.Environment;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;

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

        StringBuilder dbPath = new StringBuilder("name.db");
//        // 调试或开发环境 数据放到SD卡
//        dbPath.append(Environment.getExternalStorageDirectory().getAbsolutePath())
//                .append("/name.db");


        // 初始化
        DatabaseHelper.initialize(dbPath.toString(), 1, new Class<?>[]{
                Number.class, Word.class

        });
    }
}

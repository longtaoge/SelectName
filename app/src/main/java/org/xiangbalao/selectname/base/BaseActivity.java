package org.xiangbalao.selectname.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.xiangbalao.common.ProgressDialog;
import org.xiangbalao.common.weight.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by longtaoge on 17/2/6.
 */

public class BaseActivity extends SwipeBackActivity {


    protected ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = ProgressDialog.createDialog(this);
        dialog.setMessage("运算中...");
    }


}

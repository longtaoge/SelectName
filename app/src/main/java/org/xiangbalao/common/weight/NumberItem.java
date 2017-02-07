package org.xiangbalao.common.weight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiangbalao.selectname.R;

/**
 * Created by longtaoge on 17/2/5.
 */

public class NumberItem extends LinearLayout {


    private TextView tv_title;

    private TextView tv_des;


    private String title;
    private String des;


    public NumberItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public NumberItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_number, this, true);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);


    }

    public NumberItem(Context context) {
        super(context);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        if (!TextUtils.isEmpty(title)) {

            tv_title.setText(title);


        }


    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
        if (!TextUtils.isEmpty(des)) {
            tv_des.setText(des);


        }


    }


}

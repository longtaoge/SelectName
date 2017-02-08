package org.xiangbalao.selectname.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.xiangbalao.common.Constant;
import org.xiangbalao.common.toast.ToastUtils;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.base.BaseActivity;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private String TAG = "MainActivity";
    private Button bt_test;
    private AppCompatEditText etFirstName;
    private AppCompatEditText lastname;
    private Button bt_des;


    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setSwipeBackEnable(false);

    }


    /**
     * 初始化
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open
                , R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.canShowOverflowMenu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        bt_test = (Button) findViewById(R.id.bt_test);
        bt_test.setOnClickListener(this);
        etFirstName = (AppCompatEditText) findViewById(R.id.etFirstName);
        etFirstName.setOnClickListener(this);
        lastname = (AppCompatEditText) findViewById(R.id.lastname);
        lastname.setOnClickListener(this);
        bt_des = (Button) findViewById(R.id.bt_des);
        bt_des.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_loadPatch) {

            Intent mIntent = new Intent(MainActivity.this, ChooseNameActivity.class);
            startActivity(mIntent);

        } else if (id == R.id.nav_loadLibrary) {
            Intent mIntent = new Intent(MainActivity.this, SelectActivity.class);
            startActivity(mIntent);

        } else if (id == R.id.nav_cleanPatch) {
            Intent mIntent = new Intent(MainActivity.this, TheoryShowActivity.class);
            startActivity(mIntent);

        } else if (id == R.id.nav_killSelf) {

            //Process.killProcess(Process.myPid());
            // System.exit(0);

              try {
                  Intent data = new Intent(Intent.ACTION_SENDTO);
                  data.setData(Uri.parse("mailto:61852263@qq.com"));
                  data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
                  data.putExtra(Intent.EXTRA_TEXT, "这是内容");
                  startActivity(data);

              }catch (Exception e){


              }



        } else if (id == R.id.nav_info) {

            //打开浏览器
            openBrowser(getString(R.string.about));


        } else if (id == R.id.nav_share) {
            //分享
            shareMsg("起名", "乡吧佬起名", "开源起名测名软件 : https://github.com/longtaoge/SelectName");

        } else if (id == R.id.nav_about) {


            openBrowser(getString(R.string.about));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void shareMsg(String activityTitle, String msgTitle, String msgText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }


    private void openBrowser(String url) {


        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (ActivityNotFoundException e){

            ToastUtils.w("请安装浏览器后打开"+e.toString()).show();

        }


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
                    Intent mIntent = new Intent(MainActivity.this, NameDetailActivity.class);

                    mIntent.putExtra(Constant.NAMES, etFirstName.getText().toString().trim() + lastname.getText().toString().trim());
                    startActivity(mIntent);

                }

                break;
            case R.id.bt_des:


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

        String lastnameString = lastname.getText().toString().trim();
        if (TextUtils.isEmpty(lastnameString) || lastnameString.length() < 2) {
            ToastUtils.e("本版本只支持两字名,请输入两字名").show();
            return false;
        }


        return true;
    }


}

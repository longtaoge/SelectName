package org.xiangbalao.selectname.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.xiangbalao.selectname.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


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


        } else if (id == R.id.nav_loadLibrary) {


        } else if (id == R.id.nav_cleanPatch) {


        } else if (id == R.id.nav_killSelf) {

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

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
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }



    @Override
    protected void onResume() {


        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}

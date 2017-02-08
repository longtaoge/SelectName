package org.xiangbalao.selectname.utils;

/**
 * Created by longtaoge on 17/2/8.
 */

public class MyThread extends Thread {


    @Override
    public void run() {
        super.run();


        if (threadListener != null) {


            threadListener.onThreadRun();
        }
    }

    public interface MyThreadListener {

        void onThreadRun();


    }


    public void setThreadListener(MyThreadListener threadListener) {
        this.threadListener = threadListener;
    }

    private MyThreadListener threadListener;


}

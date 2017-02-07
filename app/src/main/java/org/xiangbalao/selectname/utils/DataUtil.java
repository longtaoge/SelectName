package org.xiangbalao.selectname.utils;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;
import org.xiangbalao.selectname.ui.SelectActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class DataUtil {

    private static String DB_PATH = "/data/data/org.xiangbalao.selectname/databases/";
    private String DB_NAME = "name.db";
    private java.lang.String ASSETS_NAME = "name.db";

    public DataUtil() {
    }

    public void initDb(Context context) {
        String[] lishu_miaoshu = context.getResources().getStringArray(R.array.lishu_miaoshu);
        String[] bihua_miaoshu = context.getResources().getStringArray(R.array.bihua_miaoshu);
        Dao<Number, Integer> dao = null;
        DatabaseHelper helper = DatabaseHelper.getInstance(context);
        try {
            dao = helper.getDao(Number.class);
            // 简单写法dao=DatabaseHelper.getInstance(OmliteActivity.this).getDao(User.class);
            // 或者 dao=DBFactory.getDao(OmliteActivity.this, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtils.i(SelectActivity.class.getSimpleName(), e.toString());
        }


        for (int i = 0; i < lishu_miaoshu.length; i++) {
            Number number = new Number();
            number.setNumber(i + 1);

            number.setBihua_miaoshu(bihua_miaoshu[i]);
            number.setLishu_miaoshu(lishu_miaoshu[i]);
            String des = lishu_miaoshu[i];
            String jixiong = des.substring(des.lastIndexOf("("), des.length());
            number.setJixiong(jixiong);


            LogUtils.i(SelectActivity.class.getSimpleName(), jixiong);


            try {
                dao.createOrUpdate(number);
            } catch (SQLException e) {
                e.printStackTrace();
                LogUtils.i(SelectActivity.class.getSimpleName(), e.toString());
            }

        }


    }


    public void initWord(Context context) {
        String[] words = context.getResources().getStringArray(R.array.word_number);

        Dao<Word, String> daoWord = null;
        DatabaseHelper helper = DatabaseHelper.getInstance(context);
        try {
            daoWord = helper.getDao(Word.class);
            // 简单写法dao=DatabaseHelper.getInstance(OmliteActivity.this).getDao(User.class);
            // 或者 dao=DBFactory.getDao(OmliteActivity.this, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtils.i(SelectActivity.class.getSimpleName(), e.toString());
        }


        for (int i = 0; i < words.length; i++) {


            String tempWord = words[i];
            String word = tempWord.substring(tempWord.lastIndexOf("＃") + 1);
            char[] chars = word.toCharArray();


            LogUtils.i(DataUtil.class.getSimpleName(), (i + 1) + "画" + word);


            for (int j = 0; j < chars.length; j++) {
                Word mWord = new Word();
                mWord.setNumber(i + 1);
                mWord.setSimplified(String.valueOf(chars[j]));

                try {
                    daoWord.createOrUpdate(mWord);
                } catch (SQLException e) {
                    e.printStackTrace();
                    LogUtils.e(SelectActivity.class.getSimpleName(), e.toString());
                }


            }


        }
        try {
            List<Word> wordList = daoWord.queryForAll();
            LogUtils.i(SelectActivity.class.getSimpleName(), wordList.size() + "__");
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtils.i(SelectActivity.class.getSimpleName(), e.toString());
        }

    }


    public void copyDataBase(Context context) throws IOException {
        File dir = new File(DB_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dbf = new File(DB_PATH + DB_NAME);
        if (!dbf.exists()) {
            // Open your local db as the input stream
            InputStream myInput = context.getAssets().open(ASSETS_NAME);
            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } else {

            LogUtils.i(DataUtil.class.getSimpleName(), "数据库已经存在");

        }


    }


}
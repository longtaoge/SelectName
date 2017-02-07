package org.xiangbalao.selectname.utils;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.selectname.SelectActivity;
import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;

import java.sql.SQLException;
import java.util.List;

public class DataUtil {
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
            number.setNumber(i+1);

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


            String tempWord=words[i];
            String word= tempWord.substring(tempWord.lastIndexOf("画")+1);
            char [] chars=word.toCharArray();


            LogUtils.i(SelectActivity.class.getSimpleName(), word);


            for (int j=0;j<chars.length;j++){
                Word mWord =new Word();
                mWord.setNumber(i+1);
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
            List<Word> wordList= daoWord.queryForAll();
            LogUtils.i(SelectActivity.class.getSimpleName(), wordList.size()+"__");
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtils.i(SelectActivity.class.getSimpleName(), e.toString());
        }

    }









}
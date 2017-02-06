package xiangbalao;

import android.app.Application;
import android.os.Environment;
import android.test.ApplicationTestCase;

import com.j256.ormlite.dao.Dao;

import org.xiangbalao.common.db.DatabaseHelper;
import org.xiangbalao.common.util.LogUtils;
import org.xiangbalao.selectname.model.Number;
import org.xiangbalao.selectname.model.Word;

import java.sql.SQLException;

import static org.xiangbalao.selectname.R.array.lishu_miaoshu;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }




    public void testSubString(){


        String des ="[1] （太极之数）太极之数，万物开泰，生发无穷，利禄亨通。 (大吉) ";
        String jixiong=des.substring(des.lastIndexOf("("),des.length());

        LogUtils.i(ApplicationTest.class.getSimpleName(),jixiong.toString());
    }




    public void testgetString() {

        StringBuilder dbPath = new StringBuilder();
        // 调试或开发环境 数据放到SD卡
        dbPath.append(Environment.getExternalStorageDirectory().getAbsolutePath())
                .append("/selectname/name.db");

        String[] arrys = getContext().getResources().getStringArray(lishu_miaoshu);


        // 初始化
        DatabaseHelper.initialize(dbPath.toString(), 1, new Class<?>[]{
                Number.class, Word.class

        });
        Dao<Number, Integer> dao = null;
        DatabaseHelper helper = DatabaseHelper.getInstance(getContext());
        try {

            dao = helper.getDao(Number.class);

            // 简单写法dao=DatabaseHelper.getInstance(OmliteActivity.this).getDao(User.class);
            // 或者 dao=DBFactory.getDao(OmliteActivity.this, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            LogUtils.i(ApplicationTest.class.getSimpleName(),e.toString());
        }





        for (int i = 0; i < arrys.length; i++) {


            LogUtils.i(ApplicationTest.class.getSimpleName(), arrys[i]);

            Number number=new Number();
            number.setNumber(i);
           // number.setId(i);

            try {

                if (dao!=null){
                    dao.createOrUpdate(number);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                LogUtils.i(ApplicationTest.class.getSimpleName(),e.toString());
            }

        }


    }


}
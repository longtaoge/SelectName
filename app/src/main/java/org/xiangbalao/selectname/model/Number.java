package org.xiangbalao.selectname.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by longtaoge on 17/2/5.
 */

public class Number implements Serializable {

    @DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
    private int id;

    @DatabaseField
    public int number;

    @DatabaseField
    private String lishu_miaoshu;

    @DatabaseField
    private String bihua_miaoshu;

    public String getJixiong() {
        return jixiong;
    }

    public void setJixiong(String jixiong) {
        this.jixiong = jixiong;
    }

    public String getBihua_miaoshu() {
        return bihua_miaoshu;
    }

    public void setBihua_miaoshu(String bihua_miaoshu) {
        this.bihua_miaoshu = bihua_miaoshu;
    }

    public String getLishu_miaoshu() {
        return lishu_miaoshu;
    }

    public void setLishu_miaoshu(String lishu_miaoshu) {
        this.lishu_miaoshu = lishu_miaoshu;
    }

    @DatabaseField
    private String jixiong;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

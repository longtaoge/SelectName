package org.xiangbalao.selectname.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by longtaoge on 17/2/5.
 */

public class Word implements Serializable  {

    @DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
    private int id;
    @DatabaseField
    public String simplified;
    @DatabaseField
    public String unsimplified;
    @DatabaseField
    public String pinyin;
    @DatabaseField
    private int number;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSimplified() {
        return simplified;
    }

    public void setSimplified(String simplified) {
        this.simplified = simplified;
    }

    public String getUnsimplified() {
        return unsimplified;
    }

    public void setUnsimplified(String unsimplified) {
        this.unsimplified = unsimplified;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

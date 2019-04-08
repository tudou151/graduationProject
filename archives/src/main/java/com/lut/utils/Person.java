package com.lut.utils;

import java.util.Date;

public class Person {

    /**
     * 创建人
     */
    protected String founder;

    /**
     * 创建日期
     */
    protected Date createTime;

    /**
     * 修改日期
     */
    protected Date modifyTime;

    /**
     * 修改人
     */
    protected String modifier;

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}

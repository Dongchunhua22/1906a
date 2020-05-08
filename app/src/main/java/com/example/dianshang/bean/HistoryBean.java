package com.example.dianshang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryBean {
    @Id
    public String keyword;
    public long time;
    @Generated(hash = 1033544588)
    public HistoryBean(String keyword, long time) {
        this.keyword = keyword;
        this.time = time;
    }
    @Generated(hash = 48590348)
    public HistoryBean() {
    }
    public String getKeyword() {
        return this.keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}

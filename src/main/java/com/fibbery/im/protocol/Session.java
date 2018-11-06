package com.fibbery.im.protocol;

/**
 * 暂时只存储用户id和名字
 * @author fibbery
 * @date 2018/11/5
 */
public class Session {

    private long userId;

    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

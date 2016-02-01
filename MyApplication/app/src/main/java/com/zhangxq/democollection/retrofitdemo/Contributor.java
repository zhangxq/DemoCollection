package com.zhangxq.democollection.retrofitdemo;

import java.io.Serializable;

/**
 * Created by zhangq on 2015/8/23.
 */
public class Contributor implements Serializable {
    private String login;
    private int contributions;

    public Contributor() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }
}

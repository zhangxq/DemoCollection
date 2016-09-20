package com.zhangxq.democollection.danmudemo;


/**
 * Created by xingbo_szd on 2016/7/19.
 */
public class Danmu {
    Danmu(){
    }

    public Danmu(int userId,String avatar,String nick,String content){
        this.userId=userId;
        this.avatar=avatar;
        this.nick=nick;
        this.content=content;
    }

    public int userId;
    public String avatar;
    public String nick;
    public String content;

}

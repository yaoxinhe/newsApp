package com.example.bawei.homemodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/21 16:45
 * @Email 1151403054@qq.com
 */
public class NewsMessageBean {

    /**
     * id : 18
     * content : 这里是评论
     * newscode : e1931236a30f11e9abe5fc7774fd634d
     * commenttime : 2020/1/21 16:43:07
     * parentid : 5
     * userid : 475
     */

    private int id;
    private String content;
    private String newscode;
    private String commenttime;
    private int parentid;
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewscode() {
        return newscode;
    }

    public void setNewscode(String newscode) {
        this.newscode = newscode;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
